package com.example.kchundur.restaurentapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.*;

import java.util.ArrayList;

/**
 * Created by kchundur on 3/10/2018.
 */

public class GetUrlInformation{

    public void getrestaurents(Context context, final ArrayList<RestaurentSummary> reslist) {

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
                        RestaurentSummary currentrestaurent=new RestaurentSummary();
                        try {
                            JSONArray results = (JSONArray) response.get("results");
                            for(int i=0;i<results.length();i++)
                            {
                                JSONObject restaurent = (JSONObject)results.get(i);
                                currentrestaurent.setRestaurentName((String)restaurent.get("name"));
                                currentrestaurent.setLatutude((int)restaurent.get("lat"));
                                currentrestaurent.setLatutude((int)restaurent.get("lng"));
                                reslist.add(currentrestaurent);
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
