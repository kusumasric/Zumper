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


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,Observer {

    private GoogleMap mMap;
    private ModelRestaurentSF modal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        modal=new ModelRestaurentSF();
        modal.addObserver(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        GetUrlInformation geturl=new GetUrlInformation(modal);

        geturl.getrestaurents(this);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


                StringBuilder placeid= (StringBuilder) marker.getTag();

                Intent intent = new Intent(getApplicationContext(),Restaurentinfo_View.class);
                intent.putExtra("placeid", placeid.toString());
                startActivity(intent);


            }
        });
        LatLng sanFrancisco = new LatLng(36, -122);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanFrancisco));
    }

    private void refreshUI(){
        for(PlacesSearchResult restaurent: modal.getRestaurents()){
            StringBuilder s=new StringBuilder();
            s.append(restaurent.placeId);
            mMap.addMarker(new MarkerOptions().position(new LatLng( restaurent.geometry.location.lat, restaurent.geometry.location.lng)).title(restaurent.name)).setTag(s);

        }
    }

    @Override
    public void update(Observable observable, Object o) {
        refreshUI();
    }
}
