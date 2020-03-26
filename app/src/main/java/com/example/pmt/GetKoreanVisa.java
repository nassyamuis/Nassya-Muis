package com.example.pmt;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GetKoreanVisa extends AsyncTask<String, String, ArrayList<String>> {

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        ArrayList<String> korean_visa = new ArrayList<String>();

        String link = global.link + "Get%20Korean%20Visa.php";

        HttpURLConnection conn = null;

        try{
            URL url = new URL(link);

            conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;

            while((line = in.readLine()) != null){
                String[] splitResult = line.split("\\|");

                /*for(int i = 0; i < splitResult.length; i++){
                    String[] split2 = splitResult[i].split(",");*/

                    korean_visa.add(splitResult[0]);
                    korean_visa.add(splitResult[1]);
                //}
            }
        }catch(Exception e){
            Log.e("Exception", e.getMessage());
        }
//        System.out.println(korean_visa);
        return korean_visa;
    }
}
