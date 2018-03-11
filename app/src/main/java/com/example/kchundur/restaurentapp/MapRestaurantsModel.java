package com.example.kchundur.restaurentapp;

import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by kchundur on 3/10/2018.
 */

public class MapRestaurantsModel extends Observable {

    private ArrayList<PlacesSearchResult> listRestaurants;


    public MapRestaurantsModel()
    {
        listRestaurants =new ArrayList<>();

    }

    public ArrayList<PlacesSearchResult> getRestaurants()
    {
        return listRestaurants;
    }

    public void addRestaurants(List<PlacesSearchResult> restaurants)
    {
        listRestaurants.addAll(restaurants);
        setChanged();
        notifyObservers();
    }


}
