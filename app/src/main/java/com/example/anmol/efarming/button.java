package com.example.anmol.efarming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button market = (Button) findViewById(R.id.online_market);
        Button Weather = (Button) findViewById(R.id.weather);
        Button quality = (Button) findViewById(R.id.quality_check);
        TextView utility=(TextView)findViewById(R.id.utility);

        if(language.languageselection!="English"){

            utility.setText("सुविधासूची");
            Weather.setText("मौसम पूर्वानुमान");
            quality.setText("गुणवत्ता जांचना");
            market.setText("बाजार");

        }

        Toast.makeText(this, LoginActivity.usertype.toString(), Toast.LENGTH_SHORT).show();
    }

    public void weather(View view){

        Intent intent=new Intent(getApplicationContext(),Weather.class);
        startActivity(intent);
    }

    public void quality(View view)
    {
        Intent intent =new Intent(getApplicationContext(),Image_processing.class);
        startActivity(intent);
    }

    public void market(View view){

        Intent intent;
        if (LoginActivity.usertype.equals("farmer")) {
         intent=new Intent(getApplicationContext(),farmer.class);       //jab radio button farmer choose kia ho tb
         startActivity(intent);
        }
        else
            {
            intent = new Intent(getApplicationContext(), consumer.class);        //jab radio button consumer choose kia ho tb
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
