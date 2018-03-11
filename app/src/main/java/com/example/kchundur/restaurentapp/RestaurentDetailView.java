package com.example.kchundur.restaurentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kchundur on 3/10/2018.
 */

public class RestaurentDetailView extends AppCompatActivity implements Observer
{
    TextView tv_restaurentName, tv_location,tv_phone, tv_rating;
    RestaurentDetailModel modelSingleRestaurent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurentinfo);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String placeid=(String)bd.get("placeId");

        modelSingleRestaurent=new RestaurentDetailModel();
        modelSingleRestaurent.addObserver(this);

        RestaurentDetailController getrestaurent=new RestaurentDetailController(modelSingleRestaurent);
        getrestaurent.getRestaurentInfo(getApplicationContext(),placeid);

        tv_restaurentName =findViewById(R.id.retaurentName);
        tv_location =findViewById(R.id.location);
        tv_phone=findViewById(R.id.phone);
        tv_rating =findViewById(R.id.rating);
    }


    @Override
    public void update(Observable observable, Object o) {
       tv_restaurentName.setText(modelSingleRestaurent.getPlaceDetails().name);
       tv_location.setText("Address:" + modelSingleRestaurent.getPlaceDetails().formattedAddress);
       tv_phone.setText("Phone Number:" + modelSingleRestaurent.getPlaceDetails().internationalPhoneNumber);
       tv_rating.setText("Rating:" + Float.toString(modelSingleRestaurent.getPlaceDetails().rating));
    }
}
