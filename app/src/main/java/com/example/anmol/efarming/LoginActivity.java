package com.example.anmol.efarming;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    int flag = 0;
    public static String usertype,defaultfarmername,defaultphone;
    public String c,cn;
    ProgressBar progressBar;
    private TextInputEditText mEmailView;
    private TextInputEditText mPasswordView;
    private String email;
    private String password;
    private Button signIn;
    private Button signUp;
    public DatabaseReference myref = FirebaseDatabase.getInstance().getReference();
    private TextView or;
    TextInputLayout mob;
    TextInputLayout pass;

    public ChildEventListener childEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (TextInputEditText) findViewById(R.id.email);

        mPasswordView = (TextInputEditText) findViewById(R.id.password);
        progressBar = (ProgressBar)findViewById(R.id.login_progress);

        mob = (TextInputLayout) findViewById(R.id.InputNumber);
        pass = (TextInputLayout) findViewById(R.id.passinput);


        signIn=(Button)findViewById(R.id.email_sign_in_button);
        signUp=(Button)findViewById(R.id.Register);
        or=(TextView)findViewById(R.id.or);
        myref = myref.child("root");

        if(language.languageselection!="English"){

            signUp.setText("पंजीकरण");

            signIn.setText("प्रवेश करे");

            or.setText("और");


            mob.setHint("अपना मोबाइल नंबर दर्ज करे");

            pass.setHint("अपना पासवर्ड दर्ज करे");

        }
        signIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void login()
    {
        progressBar.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.INVISIBLE);
        signIn.setVisibility(View.INVISIBLE);
        or.setVisibility(View.INVISIBLE);

        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        if (!(email.isEmpty() || password.isEmpty()))
        {
            flag = 0;

            myref.child("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    //Toast.makeText(LoginActivity.this, dataSnapshot.child("44664").child("name").getValue().toString(), Toast.LENGTH_SHORT).show();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            c = snapshot.getKey().toString();
                            cn = snapshot.child("password").getValue().toString();
                            if (email.equals(c))
                            {
                                flag = 1;
                                if (password.equals(cn)) {
                                    String a = snapshot.child("name").getValue().toString();
                                    String b = snapshot.child("phone number").getValue().toString();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    usertype = "farmer";
                                    defaultfarmername = a;
                                    defaultphone = b;
                                    Intent intent = new Intent(getApplicationContext(), button.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    mPasswordView.setText("");
                                    signUp.setVisibility(View.VISIBLE);
                                    signIn.setVisibility(View.VISIBLE);
                                    or.setVisibility(View.VISIBLE);

                                    Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        if (flag == 0)
                        {
                            myref.child("consumers").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        String c = snapshot.getKey().toString();
                                        String cn = snapshot.child("password").getValue().toString();
                                        if (email.equals(c)) {
                                            flag = 2;
                                            if (password.equals(cn)) {
                                                String a = snapshot.child("name").getValue().toString();
                                                String b = snapshot.child("phone number").getValue().toString();
                                                progressBar.setVisibility(View.INVISIBLE);
                                                usertype = "consumer";
                                                defaultfarmername = a;
                                                defaultphone = b;
                                                Intent intent = new Intent(getApplicationContext(), consumer.class);
                                                startActivity(intent);
                                            } else {
                                                signUp.setVisibility(View.VISIBLE);
                                                signIn.setVisibility(View.VISIBLE);
                                                or.setVisibility(View.VISIBLE);

                                                progressBar.setVisibility(View.INVISIBLE);
                                                mPasswordView.setText("");
                                                Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    if (flag == 0)
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUp.setVisibility(View.VISIBLE);
                                    signIn.setVisibility(View.VISIBLE);
                                    or.setVisibility(View.VISIBLE);
                                    mEmailView.setText("");
                                    mPasswordView.setText("");
                                    //Toast.makeText(LoginActivity.this, "You have not registered", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }

                    }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
            signUp.setVisibility(View.VISIBLE);
            signIn.setVisibility(View.VISIBLE);
            or.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Please fill the Details", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(){

       Intent intent=new Intent(getApplicationContext(),signup.class);
       startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

