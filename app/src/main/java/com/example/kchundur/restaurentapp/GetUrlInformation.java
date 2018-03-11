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
import java.util.List;

/**
 * Created by kchundur on 3/10/2018.
 */

public class GetUrlInformation {

    ModalRestaurentSF modalRestaurentSF;

    public  GetUrlInformation(ModalRestaurentSF modalRestaurentSF){
        this.modalRestaurentSF = modalRestaurentSF;
    }

    public void getrestaurents(Context context) {


        String restaurentsInSanFrancisco = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+SanFrancisco&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";

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
                        try {
                            Gson gson = new Gson();
                            JSONArray results = (JSONArray) response.get("results");
                            List<PlacesSearchResult> restaurents = new ArrayList<>();
                            for(int i=0;i<results.length();i++)
                            {
                                JSONObject restaurent = (JSONObject)results.get(i);
                                PlacesSearchResult result = gson.fromJson(restaurent.toString(), PlacesSearchResult.class);
                                restaurents.add(result);
                            }
                            modalRestaurentSF.addRestaurents(restaurents);
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
