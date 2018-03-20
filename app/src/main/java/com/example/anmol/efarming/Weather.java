package com.example.anmol.efarming;
import android.app.Activity;
import android.content.Intent;
import android.icu.text.StringSearch;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Weather extends AppCompatActivity {

    static String result="";
    static String main;
    static String description;
    static String temp;
    static String temp_min;
    static String temp_max;
    static String humidity;
    static String ground;
    static String wind;
    static String country;
    static String city;

    TextView heading_before;
    TextView heading_after;

    Button proceed_btn;
    Spinner spinner;
    EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        heading_before = (TextView) findViewById(R.id.text_1);
        heading_after = (TextView) findViewById(R.id.heading);
        location = (EditText) findViewById(R.id.location);
        spinner = (Spinner) findViewById(R.id.spinner);
        proceed_btn = (Button) findViewById(R.id.proceed_btn);




        ArrayList arrayList = new ArrayList();
        arrayList.add("Current Weather");
        arrayList.add("Weather Forecast");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);

        spinner.setAdapter(arrayAdapter);

        proceed_btn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view)

            {

                String value = spinner.getSelectedItem().toString();

                if (value.equals("Current Weather")) {
                    DownloadTask downloadTask = new DownloadTask();
                    try {
                        String result = downloadTask.execute("http://api.openweathermap.org/data/2.5/weather?q=" + location.getText().toString() + "&appid=88226d21f50d7e6bddf5e9f250922302").get();


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    DownloadTask downloadTask = new DownloadTask();
                    try {
                        result = downloadTask.execute("http://api.openweathermap.org/data/2.5/forecast?q=" + location.getText().toString() + "&appid=88226d21f50d7e6bddf5e9f250922302").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }

                Intent intent=new Intent(getApplicationContext(),data.class);
                startActivity(intent);

            }
        });


    }

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("json", result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String weather = jsonObject.getString("weather");
                    JSONArray weatherarray = new JSONArray(weather);
                    for (int i = 0; i < weatherarray.length(); ++i) {
                        JSONObject weatherpart = weatherarray.getJSONObject(i);
                        main = weatherpart.getString("main");
                        description = weatherpart.getString("description");
                    }
                    temp = jsonObject.getJSONObject("main").getString("temp");
                    temp_max = jsonObject.getJSONObject("main").getString("temp_max");
                    temp_min = jsonObject.getJSONObject("main").getString("temp_min");
                    humidity = jsonObject.getJSONObject("main").getString("humidity");
                    ground = jsonObject.getJSONObject("main").getString("ground_level");
                    // wind=jsonObject.getJSONObject("wind").getString("speed");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


        }
    }


    }
