package com.example.pmt;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetComparison extends AsyncTask<String, ArrayList, ArrayList> {
    private ArrayList<String> result_details = new ArrayList<String>();

    private Context c;
    public GetComparison(Context c){
        this.c = c;
    }

    protected ArrayList<String> doInBackground(String[] objects) {
        String link = global.link + "GetComparison.php?id=" + objects[0];
        HttpURLConnection conn = null;
        try{
            URL url= new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                String[] split = line.split("\\|");
                for(int i = 0; i < split.length; i++){
                    result_details.add(split[i]);
                }
            }

        }catch (Exception e){

        }
        return result_details;
    }
}
