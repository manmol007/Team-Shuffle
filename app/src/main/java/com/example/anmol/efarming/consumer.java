package com.example.anmol.efarming;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class consumer extends AppCompatActivity {

    RecyclerViewAdapter rev = new RecyclerViewAdapter(this,new ArrayList<productDetail>());
    RecyclerView recyclerView;
    public RecyclerView.Adapter aadapter;
    int a = 0;
    Spinner cropgroup,cropname;
    String scropname,scropgroup;
    ProgressDialog progressDialog ;
    DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("root");

    List<productDetail> productDetailList = new ArrayList<>();
    productDetail product;

    String []str = {"rabi","kharif"};
    String []str1={"Paddy","Jowar","Bajra","Maize","Ragi","Arhar","Moong","Urad","Cotton","Groundnut","Sunflower","Soyabean","Seasome"};
    String []str2={"Wheat","Barley","Gram","Masur","Masturd","Safflower","Toria"};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        progressDialog = new ProgressDialog(consumer.this);
        cropgroup = (Spinner)findViewById(R.id.cropgroup);
        cropname = (Spinner)findViewById(R.id.cropname);

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(consumer.this));
        final ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_style,str);
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
                    else if (scropgroup.equals("kharif"))
                    {
                        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_style,str1);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        cropname.setAdapter(adapter);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cropname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scropgroup = cropgroup.getSelectedItem().toString();
                scropname = cropname.getSelectedItem().toString();
                rev.clear();
                progressDialog.setMessage("Updating Your Product List");
                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);

                myref.child(scropgroup).child(scropname).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        productDetailList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            product = snapshot.getValue(productDetail.class);
                            productDetailList.add(product);

                        }
                        {
                            {

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Do something after 100ms
                                        progressDialog.cancel();
                                        if (productDetailList.isEmpty())
                                        {
                                            Toast.makeText(consumer.this, "empty list", Toast.LENGTH_SHORT).show();
                                        }
                                        else

                                        {
                                            Toast.makeText(consumer.this, "getting list", Toast.LENGTH_SHORT).show();
                                        }
                                        aadapter = new RecyclerViewAdapter(consumer.this, productDetailList);
                                        recyclerView.setAdapter(aadapter);
                                    }
                                }, 4000);


                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void BUY(View view){
        Intent intent=new Intent(getApplicationContext(),CreditDebitFragment.class);
        startActivity(intent);
    }
    public void callme()
    {

    }
}
