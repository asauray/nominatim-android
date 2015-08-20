package com.goodway.nominatimparser;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Antoine Sauray
 * @version 0.5
 * Licensed under the Apache2 license
 */

public class Request {

    public static void getPlaces(Action a, ArrayList<Pair> ... parameters){
        try{
            new GetPlaces(a, parameters).execute();
            Log.d("getPlaces", "getPlaces");
        }
        catch(IllegalStateException e){
            Log.e(e.getMessage(), "exception");
        }

    }

    private static class GetPlaces extends AsyncTask<Pair, Place, Void>{

    /*
        wiki : http://wiki.openstreetmap.org/wiki/Nominatim

        street=<housenumber> <streetname>
        city=<city>
        county=<county>
        state=<state>
        country=<country>
        postalcode=<postalcode>

        use q= if you don't know whether the user type an address, a city a county or whatever
    */

        private final String QUERY = "http://nominatim.openstreetmap.org/search?";
        private Action action;
        private ArrayList<Pair>[] parameters;

        /**
         *
         * @param action The method to apply on each Place which is returned by nominatim
         * @param parameters A set of keys and values to provide to the request. Each map will be triggered in a different request
         * @see Action
         */
        public GetPlaces(Action action, ArrayList<Pair> ... parameters){
            this.action = action;
            this.parameters = parameters;
        }

        @Override
        protected Void doInBackground(Pair... params) {
            StringBuilder jsonResult = new StringBuilder();
            StringBuilder sb = new StringBuilder(QUERY);
            sb.append("format=json&polygon=0&addressdetails=0&");
            for(ArrayList<Pair> pairs : parameters){
                Log.d("size="+pairs.size(), "arraylist found");
                for(Pair p : pairs){
                    sb.append(p.first+"=" + p.second+"&");
                    Log.d("p.first="+p.first+" & o.second"+p.second, "pairs");
                }
                try {
                    URL url = new URL(sb.toString());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());

                    BufferedReader jsonReader = new BufferedReader(in);
                    String lineIn;
                    while ((lineIn = jsonReader.readLine()) != null) {
                        jsonResult.append(lineIn);
                    }

                    JSONObject jsonObj;
                    try {
                        JSONArray jsonArray= new JSONArray(jsonResult.toString());
                        int length = jsonArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            long place_id = jsonObject.optLong("place_id");
                            String license = jsonObject.optString("license");
                            String osm_type = jsonObject.optString("osm_type");
                            long osm_id = jsonObject.optLong("osm_id");
                            JSONArray boundingArray = jsonObject.getJSONArray("boundingbox");
                            BoundingBox boundingBox = new BoundingBox();
                            for(int j=0; i< boundingArray.length();i++){
                                boundingBox.setBound(i, boundingArray.optDouble(i));
                            }
                            double lat = jsonObject.optDouble("lat");
                            double lon = jsonObject.optDouble("lon");
                            String display_name = jsonObject.optString("display_name");
                            String entityClass = jsonObject.optString("class");
                            String type = jsonObject.optString("type");
                            float importance = (float) jsonObject.optDouble("importance");

                            publishProgress(new Place(place_id, osm_id, lat, lon, importance, license, osm_type, display_name, entityClass, type, boundingBox));
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return null;
        }


        @Override
        protected void onProgressUpdate(Place...progress){
            action.action(progress[0]);
            Log.d(progress[0].toString(), "entity");
        }

    }
}

