package com.example.kchundur.restaurentapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeolocationApi;
import com.google.maps.internal.DateTimeAdapter;
import com.google.maps.internal.DayOfWeekAdapter;
import com.google.maps.internal.DistanceAdapter;
import com.google.maps.internal.DurationAdapter;
import com.google.maps.internal.FareAdapter;
import com.google.maps.internal.GeolocationResponseAdapter;
import com.google.maps.internal.InstantAdapter;
import com.google.maps.internal.LatLngAdapter;
import com.google.maps.internal.LocalTimeAdapter;
import com.google.maps.internal.PriceLevelAdapter;
import com.google.maps.internal.SafeEnumAdapter;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AddressType;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.Fare;
import com.google.maps.model.LocationType;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.LocalTime;
import org.json.JSONObject;

/**
 * Created by kchundur on 3/10/2018.
 */

public class RestaurentDetailController {

    RestaurentDetailModel modelsingle;

    public RestaurentDetailController(RestaurentDetailModel model)
    {
        this.modelsingle=model;
    }

    public void getRestaurentInfo(Context context, String placeid) {

        String restaurentsInSanFrancisco = "https://maps.googleapis.com/maps/api/place/details/json?placeid="
                +placeid
                +"&key=AIzaSyB-bpw0ollWA5AKpT11Y2CL2qPFs4kC_dk";

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
                        Gson gson = new GsonBuilder()
                                .registerTypeAdapter(DateTime.class, new DateTimeAdapter())
                                .registerTypeAdapter(Distance.class, new DistanceAdapter())
                                .registerTypeAdapter(Duration.class, new DurationAdapter())
                                .registerTypeAdapter(Fare.class, new FareAdapter())
                                .registerTypeAdapter(LatLng.class, new LatLngAdapter())
                                .registerTypeAdapter(
                                        AddressComponentType.class,
                                        new SafeEnumAdapter<AddressComponentType>(AddressComponentType.UNKNOWN))
                                .registerTypeAdapter(
                                        AddressType.class, new SafeEnumAdapter<AddressType>(AddressType.UNKNOWN))
                                .registerTypeAdapter(
                                        TravelMode.class, new SafeEnumAdapter<TravelMode>(TravelMode.UNKNOWN))
                                .registerTypeAdapter(
                                        LocationType.class, new SafeEnumAdapter<LocationType>(LocationType.UNKNOWN))
                                .registerTypeAdapter(
                                        PlaceDetails.Review.AspectRating.RatingType.class, new SafeEnumAdapter<PlaceDetails.Review.AspectRating.RatingType>(PlaceDetails.Review.AspectRating.RatingType.UNKNOWN))
                                .registerTypeAdapter(OpeningHours.Period.OpenClose.DayOfWeek.class, new DayOfWeekAdapter())
                                .registerTypeAdapter(PriceLevel.class, new PriceLevelAdapter())
                                .registerTypeAdapter(Instant.class, new InstantAdapter())
                                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                                .registerTypeAdapter(GeolocationApi.Response.class, new GeolocationResponseAdapter())
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

                        PlaceDetails result = null;
                        try {
                            JSONObject res = (JSONObject)response.get("result");
                            result = gson.fromJson(res.toString(), PlaceDetails.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        modelsingle.setPlaceDetails(result);


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
