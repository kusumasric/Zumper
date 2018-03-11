package com.example.kchundur.restaurentapp;

import android.content.Context;

import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by kchundur on 3/10/2018.
 */

public class ModelRestaurentSF extends Observable {

    private ArrayList<PlacesSearchResult> listRestaurents;


    public ModelRestaurentSF()
    {
        listRestaurents=new ArrayList<PlacesSearchResult>();

    }

    public ArrayList<PlacesSearchResult> getRestaurents()
    {
        return listRestaurents;
    }

    public void  addRestaurents(List<PlacesSearchResult> restaurents)
    {
        listRestaurents.addAll(restaurents);
        setChanged();
        notifyObservers();
    }


}
