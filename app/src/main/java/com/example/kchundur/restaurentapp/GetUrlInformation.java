package com.example.kchundur.restaurentapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.maps.model.PlacesSearchResult;

import org.json.JSONException;
import org.json.*;

import java.util.ArrayList;

/**
 * Created by kchundur on 3/10/2018.
 */

public class GetUrlInformation{

    public void getrestaurents(Context context, final ArrayList<PlacesSearchResult> reslist) {

        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+SanFrancisco&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("debug", "came here");
                        Log.d("retaurents","got"+response);
                        try {
                            JSONArray results = (JSONArray) response.get("results");

                            for(int i=0;i<results.length();i++)
                            {

                                JSONObject restaurent = (JSONObject)results.get(i);
                                Gson gson = new Gson();
                                com.google.maps.model.PlacesSearchResult result = gson.fromJson(restaurent.toString(), com.google.maps.model.PlacesSearchResult.class);
                                reslist.add(result);
                            }
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
