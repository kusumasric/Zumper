package com.example.kchundur.restaurentapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.model.PlacesSearchResult;

import java.util.Observable;
import java.util.Observer;


public class MapRestaurantsView extends FragmentActivity implements OnMapReadyCallback,Observer {

    private GoogleMap mMap;
    MapRestaurantsController mapRestaurantsController;
    private MapRestaurantsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        model =new MapRestaurantsModel();
        model.addObserver(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mapRestaurantsController =new MapRestaurantsController(model);
        mapRestaurantsController.getRestaurants(this);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {

                StringBuilder placeId = (StringBuilder) marker.getTag();

                Intent intent = new Intent(getApplicationContext(),RestaurentDetailView.class);
                intent.putExtra("placeId", placeId.toString());
                startActivity(intent);

            }
        });

        LatLng sanFrancisco = new LatLng(36, -122);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanFrancisco));
    }

    private void refreshUI(){

        for(PlacesSearchResult restaurant: model.getRestaurants()){

            StringBuilder s=new StringBuilder();
            s.append(restaurant.placeId);
            mMap.addMarker(
                    new MarkerOptions()
                            .position(new LatLng( restaurant.geometry.location.lat, restaurant.geometry.location.lng))
                            .title(restaurant.name)
            ).setTag(s);

        }
    }

    @Override
    public void update(Observable observable, Object o) {
        refreshUI();
    }
}
