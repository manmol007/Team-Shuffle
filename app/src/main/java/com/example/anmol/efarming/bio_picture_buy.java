package com.example.anmol.efarming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class bio_picture_buy extends AppCompatActivity {

    public RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_picture_buy);
        // Initialize RatingBar
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void rateMe(View view){

        Toast.makeText(getApplicationContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_LONG).show();}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        }
    }
