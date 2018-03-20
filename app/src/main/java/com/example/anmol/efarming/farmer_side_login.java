package com.example.anmol.efarming;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class farmer_side_login extends AppCompatActivity {

    public TextView Description;
    public TextView Quantity;
    public TextView Rate;
    public EditText quantityedittext;
    public EditText descriptionedittext;
    public EditText rateedittext;
    public ImageView gallery;
    public ImageView camera;
    public Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_side_login);

        Quantity=(TextView)findViewById(R.id.quantity);
        Description=(TextView)findViewById(R.id.Description);
        Rate=(TextView)findViewById(R.id.price);
        quantityedittext=(EditText)findViewById(R.id.quantityedittext);
        descriptionedittext=(EditText)findViewById(R.id.descriptionedittext);
        rateedittext=(EditText)findViewById(R.id.rateedittext);
        gallery=(ImageView)findViewById(R.id.Gallery);
        submit=(Button)findViewById(R.id.submit);


        if(language.languageselection !="English"){

            Quantity.setText("मात्रा");
            Description.setText("विवरण");
            Rate.setText("मूल्य प्रति यूनिट क्विंटल");
            descriptionedittext.setHint("िवरण डाले");
            quantityedittext.setHint("मात्रा डाले");
            rateedittext.setHint("मूल्य प्रति यूनिट िंटल डाले");


        }

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("Images/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send quantity rat nd description and image to firebase
            }
        });

    }
}
