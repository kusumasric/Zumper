package com.example.kchundur.restaurentapp;

import com.google.maps.model.PlaceDetails;

import java.util.Observable;

/**
 * Created by kchundur on 3/10/2018.
 */

public class ModelSingleRestaurent extends Observable {

    private PlaceDetails placedetail;

    public ModelSingleRestaurent()
    {
        placedetail=new PlaceDetails();
    }

    public PlaceDetails getPlaceDetails()
    {
        return placedetail;
    }

    public void setPlacedetail(PlaceDetails place)
    {
        placedetail=place;
        setChanged();
        notifyObservers();
    }
}
