package com.example.pmt;

import android.os.AsyncTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GetPackages extends AsyncTask<String, ArrayList, ArrayList> {
    private ArrayList<String> pckg_price = new ArrayList<String>();
    private ArrayList<String> pckg_season = new ArrayList<String>();
    private ArrayList<String> pckg_start = new ArrayList<String>();
    private ArrayList<String> pckg_end = new ArrayList<String>();
    private ArrayList<String> agent_contact = new ArrayList<String>();
    private ArrayList<String> pckg_title = new ArrayList<String>();
    //private ArrayList<Bitmap> pckg_image = new ArrayList<Bitmap>();

    private Context c;
    private GridView grid;

    public GetPackages(Context c, GridView g){
        this.c = c;
        grid = g;
    }

    @Override
    protected ArrayList<ArrayList> doInBackground(String[] objects) {
        ArrayList<ArrayList> result = new ArrayList<>();

        String link = global.link + "Get%20Packages.php?Season=" + objects[0] + "&budget=" + objects[1];

        HttpURLConnection conn = null;

        try{
            URL url= new URL(link);

            conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;

            while((line=in.readLine()) != null){
                String[] split = line.split("\\|");
                pckg_price.add(split[0]);
                pckg_season.add(split[1]);
                pckg_start.add(split[2]);
                pckg_end.add(split[3]);
                agent_contact.add(split[4]);
                pckg_title.add(split[5]);
                //pckg_image.add(getImage(split[6]));
            }

            result.add(pckg_price);
            result.add(pckg_season);
            result.add(pckg_start);
            result.add(pckg_end);
            result.add(agent_contact);
            result.add(pckg_title);
            //result.add(pckg_image);
        }catch (Exception e){

        }
        return result;
    }

    /*@Override
    public void onPostExecute(Void aVoid) {
        PackageAdaptor packages = new PackageAdaptor(c, pckg_price, pckg_season, pckg_start, pckg_end, agent_contact, pckg_title, pckg_image);
        grid.setAdapter(packages);
    }*/

    /*public Bitmap getImage(String imageName){
        String link = imageName;

        HttpURLConnection conn = null;

        try{
            URL url = new URL(link);

            conn = (HttpURLConnection) url.openConnection();

            return BitmapFactory.decodeStream(conn.getInputStream());
        }catch (Exception e){
            return null;
        }
    }*/
}
