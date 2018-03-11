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
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchundur on 3/10/2018.
 */

public class GetSingleRestaurentInfo {


    private String placeid;
    ModelSingleRestaurent modelsingle;

    public  GetSingleRestaurentInfo(ModelSingleRestaurent model)
    {
        this.modelsingle=model;
    }

    public void getARestaurentInfo(Context context,String placeid) {


        String restaurentsInSanFrancisco = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeid+"&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                restaurentsInSanFrancisco,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("debug", "came here");
                        Log.d("retaurents","got"+response);
                        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

                        PlaceDetails result = null;
                        try {
                            JSONObject res = (JSONObject)response.get("result");
                            result = gson.fromJson(res.toString(), PlaceDetails.class);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        modelsingle.setPlacedetail(result);


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
