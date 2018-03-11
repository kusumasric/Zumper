package com.example.kchundur.restaurentapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.model.PlacesSearchResult;

import org.json.JSONException;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchundur on 3/10/2018.
 */

public class MapRestaurantsController {

    MapRestaurantsModel mapRestaurantsModel;

    private static final String RESTAURANTS_IN_SAN_FRANCISCO = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+SanFrancisco&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";


    public MapRestaurantsController(MapRestaurantsModel mapRestaurantsModel){
        this.mapRestaurantsModel = mapRestaurantsModel;
    }

    public void getRestaurants(Context context) {

        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                RESTAURANTS_IN_SAN_FRANCISCO,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<PlacesSearchResult> restaurants = new ArrayList<>();

                            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                            JSONArray results = (JSONArray) response.get("results");

                            for(int i=0; i<results.length(); i++)
                            {
                                JSONObject restaurant = (JSONObject)results.get(i);
                                PlacesSearchResult placesSearchResult = gson.fromJson(restaurant.toString(), PlacesSearchResult.class);
                                restaurants.add(placesSearchResult);
                            }

                            mapRestaurantsModel.addRestaurants(restaurants);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.d("resonse",error.toString());

                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        queue.add(jsonObjectRequest);

    }


}
