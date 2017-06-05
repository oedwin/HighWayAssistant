package com.example.student.highwayassistant;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by student on 1/4/17.
 */

public class DataParser {
    public List<HashMap<String, String>> parse(String jsonData) {
        try {
            JSONArray jsonArray = null;
            JSONObject jsonObject;

            try {
                Log.d("Places", "parse");
                jsonObject = new JSONObject((String) jsonData);
                jsonArray = jsonObject.getJSONArray("results");
            } catch (Exception e) {
                Log.d("Places", "parse error");
                e.printStackTrace();
            }
            return getPlaces(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        try {
            int placesCount = jsonArray.length();
            List<HashMap<String, String>> placesList = new ArrayList<>();
            HashMap<String, String> placeMap = null;
            Log.d("Places", "getPlaces");

            for (int i = 0; i < placesCount; i++) {
                try {
                    placeMap = getPlace((JSONObject) jsonArray.get(i));
                    placesList.add(placeMap);
                    Log.d("Places", "Adding places");

                } catch (JSONException e) {
                    Log.d("Places", "Error in Adding places");
                    e.printStackTrace();
                }
            }
            return placesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        HashMap<String, String> googlePlaceMap = new HashMap<String, String>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String formatted_address="-NA-";
        String formatted_phone="-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        Log.d("getPlace", "Entered");

        try {
            // Extracting Place name, if available
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            // Extracting Place Vicinity, if available
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }
            // Extracting Place formatted_address, if available
            if(!googlePlaceJson.isNull("formatted_address")){
                formatted_address = googlePlaceJson.getString("formatted_address");

            }
            // Extracting Place formatted_phone, if available
            if(!googlePlaceJson.isNull("formatted_phone_number")){
                formatted_phone = googlePlaceJson.getString("formatted_phone_number");
            }
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJson.getString("reference");
            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("formatted_address", formatted_address);
            googlePlaceMap.put("formatted_phone", formatted_phone);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
            Log.d("getPlace", "Putting Places");
        } catch (JSONException e) {
            Log.d("getPlace", "Error");
            e.printStackTrace();
        }
        return googlePlaceMap;
    }
}
