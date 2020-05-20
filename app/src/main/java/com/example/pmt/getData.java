package com.example.pmt;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class getData extends AsyncTask<String, ArrayList, ArrayList> {
    private ArrayList<String> result_details = new ArrayList<String>();
    private Context c;
    public getData(Context c){
        this.c = c;
    }

    protected ArrayList<String> doInBackground(String[] objects) {
        String link = global.link + "getPackagesID.php?id=" + objects[0];
        HttpURLConnection conn = null;
        try{
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                if(!line.equals("")){
                    String[] split = line.split("\\|");
                    result_details.add(split[0]); //package id
                    result_details.add(split[1]); //title
                    result_details.add(split[2]); //price
                    result_details.add(split[3]); //season
                    result_details.add(split[4]); //depart
                    result_details.add(split[5]); //arrival
                    result_details.add(split[6]); //agent's contact
                }
            }

            String link_itinerary = global.link + "getItinerary.php?id=" + objects[0];
            HttpURLConnection conn_itinerary = null;

            //connect webserver to get itinerary
            URL url_itinerary= new URL(link_itinerary);
            conn_itinerary = (HttpURLConnection) url_itinerary.openConnection();
            conn_itinerary.connect();
            BufferedReader in_itinerary = new BufferedReader(new InputStreamReader(conn_itinerary.getInputStream()));
            String line_itinerary;
            while((line_itinerary = in_itinerary.readLine()) != null){
                if(!line_itinerary.equals("")){
                    String[] split = line_itinerary.split("\\|");
                    result_details.add("Day " + split[0]); //itinerary's label
                    result_details.add(split[1]); //itinerary's details
                    }
                }
        }catch (Exception e){

        }
        return result_details;
    }
}
