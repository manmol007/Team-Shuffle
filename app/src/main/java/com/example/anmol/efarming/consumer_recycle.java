package com.example.anmol.efarming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class consumer_recycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_recycle);
        RecyclerView farmersList = (RecyclerView) findViewById(R.id.farmersList);
        farmersList.setLayoutManager(new LinearLayoutManager(this));
        String [] languages = {"jayanti lal meena","mahendra singh","kanti lal devda","ramlal thakur","harshul","anmol"};
        String [] info = {"1995/qp","1986/ql","1997/ql","1780/ql","null","null"};
        farmersList.setAdapter(new farmingListAdapter(languages,info));


    }
}
