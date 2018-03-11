package com.example.kchundur.restaurentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kchundur on 3/10/2018.
 */

public class Restaurentinfo_View extends AppCompatActivity implements Observer
{
    TextView restaurentName,location;
    ModelSingleRestaurent modelSingleRestaurent;
    String placeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurentinfo);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd!=null)
        {
            placeid=(String)bd.get("placeid");
        }
        modelSingleRestaurent=new ModelSingleRestaurent();
        modelSingleRestaurent.addObserver(this);
        restaurentName=(TextView)findViewById(R.id.retaurentName);
        location=(TextView)findViewById(R.id.location);
        GetSingleRestaurentInfo getrestaurent=new GetSingleRestaurentInfo(modelSingleRestaurent);
        modelSingleRestaurent.addObserver(this);
        getrestaurent.getARestaurentInfo(getApplicationContext(),placeid);
    }


    @Override
    public void update(Observable observable, Object o) {
       restaurentName.setText(modelSingleRestaurent.getPlaceDetails().name);
    }
}
