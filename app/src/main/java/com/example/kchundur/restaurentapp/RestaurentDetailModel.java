package com.example.kchundur.restaurentapp;

import com.google.maps.model.PlaceDetails;

import java.util.Observable;

/**
 * Created by kchundur on 3/10/2018.
 */

public class RestaurentDetailModel extends Observable {

    private PlaceDetails placeDetails;

    public RestaurentDetailModel()
    {
        placeDetails =new PlaceDetails();
    }

    public PlaceDetails getPlaceDetails()
    {
        return placeDetails;
    }

    public void setPlaceDetails(PlaceDetails place)
    {
        placeDetails =place;
        setChanged();
        notifyObservers();
    }
}
