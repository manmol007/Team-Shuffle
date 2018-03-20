package com.example.anmol.efarming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class language extends AppCompatActivity {

    Spinner language;
    public static String languageselection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        language=(Spinner)findViewById(R.id.Languagespinner);
        String []str={"English","हिंदी"};
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.spinner_style,str);
        language.setAdapter(adapter);

    }
    public void go(View view){

        String spinnerlanguage= language.getSelectedItem().toString();

        if(spinnerlanguage=="English"){
            languageselection="English";
        }
        else
        {
            languageselection="हिंदी";
        }
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
