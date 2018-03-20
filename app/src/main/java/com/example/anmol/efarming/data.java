package com.example.anmol.efarming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class data extends AppCompatActivity {

    TextView detail1;
    TextView detail3;
    TextView detail4;
    TextView detail5;
    TextView detail6;
    TextView detail2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        detail1 = (TextView) findViewById(R.id.detail1);
        detail2 = (TextView) findViewById(R.id.detail2);
        detail3 = (TextView) findViewById(R.id.detail3);
        detail4 = (TextView) findViewById(R.id.detail4);
        detail5 = (TextView) findViewById(R.id.detail5);
        detail6 = (TextView) findViewById(R.id.detail6);

        detail1.setText("main:"+Weather.main);
        detail2.setText("Description:"+Weather.description);
         detail3.setText("Temperature:"+Weather.temp);
        detail4.setText("Minimum Temperature:"+Weather.temp_min);
        detail5.setText("Maximum Temperature:"+Weather.temp_max);
        detail6.setText("Humidity:"+Weather.humidity);

    }
}
