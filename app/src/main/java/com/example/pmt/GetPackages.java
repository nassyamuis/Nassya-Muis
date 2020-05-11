package com.example.pmt;

import android.graphics.Matrix;
import android.os.AsyncTask;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetPackages extends AsyncTask<String, ArrayList, ArrayList> {
    private ArrayList<String> pckg_price = new ArrayList<String>();
    private ArrayList<String> pckg_season = new ArrayList<String>();
    private ArrayList<String> pckg_start = new ArrayList<String>();
    private ArrayList<String> pckg_end = new ArrayList<String>();
    private ArrayList<String> agent_contact = new ArrayList<String>();
    private ArrayList<String> pckg_title = new ArrayList<String>();
    private ArrayList<Bitmap> pckg_image = new ArrayList<Bitmap>();
    private ArrayList<String> pckg_id = new ArrayList<String>();

    private Context c;
    private GridView grid;

    public GetPackages(Context c, GridView g){
        this.c = c;
        grid = g;
    }

    @Override
    protected ArrayList<ArrayList> doInBackground(String[] objects) {
        //connect webserver to get packages
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
                pckg_image.add(getImage(split[6]));
                pckg_id.add(split[7]);
            }

            //get packages information
            result.add(pckg_price);
            result.add(pckg_season);
            result.add(pckg_start);
            result.add(pckg_end);
            result.add(agent_contact);
            result.add(pckg_title);
            result.add(pckg_image);
            result.add(pckg_id);
        }catch (Exception e){

        }
        return result;
    }

    public Bitmap getImage(String imageName){
        String link = imageName;
        HttpURLConnection conn = null;

        try{
            URL url = new URL(link);

            conn = (HttpURLConnection) url.openConnection();
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            //resize the image
            bmOptions.inSampleSize = 10; // 1 = 100% if you write 4 means 1/4 = 25%
            Bitmap result = BitmapFactory.decodeStream(conn.getInputStream());
            return getResizedBitmap(result, 300,500);
        }catch (Exception e){
            return null;
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0,
                width, height, matrix, false);

        return resizedBitmap;
    }
}
