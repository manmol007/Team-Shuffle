package com.example.anmol.efarming;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class farmer extends AppCompatActivity{

    DatabaseReference rabi = FirebaseDatabase.getInstance().getReference().child("root").child("rabi");
    DatabaseReference kharif = FirebaseDatabase.getInstance().getReference().child("root").child("kharif");
    StorageReference mstorage = FirebaseStorage.getInstance().getReference().child("images");
    public EditText fname,qty,price,des;
    public String scropname,scropgroup,sname,squantity,sprice,sdesc;
    public Spinner cropgroup,cropname;
    ImageView img;
    public Uri imguri;
    String []str = {"rabi","kharif"};
    String []str1={"Paddy","Jowar","Bajra","Maize","Ragi","Arhar","Moong","Urad","Cotton","Groundnut","Sunflower","Soyabean","Seasome"};
    String []str2={"Wheat","Barley","Gram","Masur","Masturd","Safflower","Toria"};

    TextView gall;
    Button selectimage,viewprice;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);

        open();
        fname = (EditText)findViewById(R.id.farmername);
        fname.setText(LoginActivity.defaultfarmername);
        qty = (EditText)findViewById(R.id.quantity);
        price = (EditText)findViewById(R.id.price);
        des = (EditText)findViewById(R.id.desc);
        cropgroup = (Spinner)findViewById(R.id.cropgroupspinner);
        cropname = (Spinner)findViewById(R.id.cropnamespinner);
        img = (ImageView)findViewById(R.id.img1);
        gall = (TextView)findViewById(R.id.galery);
        selectimage = (Button)findViewById(R.id.select);
        viewprice = (Button)findViewById(R.id.view);

        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_style,str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cropgroup.setAdapter(adapter);

        cropgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scropgroup = cropgroup.getSelectedItem().toString();
                if (scropgroup.equals("rabi"))
                {

                    ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_style,str2);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cropname.setAdapter(adapter);
                }
                else
                {

                    ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_style,str1);
//                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.array_name2, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cropname.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        viewprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(farmer.this, "Prices will show here", Toast.LENGTH_SHORT).show();
            }
        });
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
                Intent pictureActionIntent = null;
                pictureActionIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(
                        pictureActionIntent,
                        1);
            }
        });
        viewprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),Imageview.class);
                startActivity(intent);
            }
        });
        gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rabi = FirebaseDatabase.getInstance().getReference().child("root").child("rabi");
                kharif = FirebaseDatabase.getInstance().getReference().child("root").child("kharif");
                progressDialog = new ProgressDialog(farmer.this);
                progressDialog.setMessage("uploading product");
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.setCancelable(false);

                if((Integer.parseInt(price.getText().toString())<=1300)){
                    price.setText("");
                    Toast.makeText(getApplicationContext(),"your upload price is less than 1300 which is MST by govt.",Toast.LENGTH_LONG).show();
                }
                callme();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


         if (resultCode == RESULT_OK && requestCode == 1) {
            if (data != null) {

                imguri = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                img.setImageURI( imguri );

            } else {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }


    }
    void callme()
    {
        sname = fname.getText().toString();
        sdesc = des.getText().toString();
        sprice = price.getText().toString();
        squantity = qty.getText().toString();
        scropgroup = cropgroup.getSelectedItem().toString();
        scropname = cropname.getSelectedItem().toString();
        if ((imguri == null)||(sname.isEmpty()||sdesc.isEmpty()||sprice.isEmpty()||squantity.isEmpty()))
        {
            Toast.makeText(this, "Fields Can't Be Empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.show();
            if (scropgroup.equals("rabi"))
            {
                rabi = rabi.child(scropname).push();

                rabi.child("farmername").setValue(sname);
                rabi.child("price").setValue(sprice);
                rabi.child("description").setValue(sdesc);
                rabi.child("quantity").setValue(squantity);
                rabi.child("cropgroup").setValue("rabi");
                rabi.child("cropname").setValue(scropname);

                mstorage.child("rabi").child(scropname).child(imguri.getLastPathSegment()).putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getDownloadUrl().toString();
                        rabi.child("imageurl").setValue(url);
                        Toast.makeText(farmer.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(farmer.this,button.class);
                        startActivity(intent);
                    }
                });

            }
            else
            {

                kharif = kharif.child(scropname).push();

                kharif.child("farmername").setValue(sname);
                kharif.child("price").setValue(sprice);
                kharif.child("description").setValue(sdesc);
                kharif.child("quantity").setValue(squantity);
                kharif.child("cropgroup").setValue(scropgroup);
                kharif.child("cropname").setValue(scropname);

                mstorage.child("kharif").child(scropname).child(imguri.getLastPathSegment()).putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getDownloadUrl().toString();
                        rabi.child("imageurl").setValue(url);
                        Toast.makeText(farmer.this, "Product Uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(farmer.this,button.class);
                        startActivity(intent);
                    }
                });

            }
        }
    }

    private void open() {
        if (checkPermission()) {
            Toast.makeText( this, "Permission already granted.", Toast.LENGTH_SHORT ).show();

        } else {

            requestPermission();

        }

    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission( getApplicationContext(), INTERNET );
        int result1 = ContextCompat.checkSelfPermission( getApplicationContext(), CAMERA );
        int result2 = ContextCompat.checkSelfPermission( getApplicationContext(), WRITE_EXTERNAL_STORAGE );


        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions( this, new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, INTERNET}, 1 );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale( CAMERA )) {
                            showMessageOKCancel( "You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions( new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, INTERNET},
                                                        1 );
                                            }
                                        }
                                    } );
                            return;
                        }
                    }

                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(farmer.this )
                .setMessage( message )
                .setPositiveButton( "OK", okListener )
                .create()
                .show();
    }
}
