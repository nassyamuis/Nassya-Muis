package com.example.pmt;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.Toast;

import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class DisplayAndCompare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_and_compare);
        try {
            Intent data_pckg = getIntent();
            int inputted_budget = data_pckg.getIntExtra("budget", 20000000);
            String inputted_season = data_pckg.getStringExtra("season");

            GridView show_pckg = findViewById(R.id.pckg_details);

            GetPackages pckg_input = new GetPackages(this, show_pckg);

            pckg_input.execute(inputted_season, "IDR " + inputted_budget);

            ArrayList<ArrayList> result = pckg_input.get();
            ArrayList<String> price_result = result.get(0);

            ArrayList<String> pckg_price = result.get(0);
            ArrayList<String> pckg_season = result.get(1);
            ArrayList<String> pckg_start = result.get(2);
            ArrayList<String> pckg_end = result.get(3);
            ArrayList<String> agent_contact = result.get(4);
            ArrayList<String> pckg_title = result.get(5);
            ArrayList<String> url = (result.get(7));
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            ArrayList<Bitmap> bmp = getImageBitmap(result.get(6));
            ArrayList<Bitmap> pckg_image = result.get(6);

            PackageAdaptor packages = new PackageAdaptor(this, pckg_price, pckg_season, pckg_start, pckg_end, agent_contact, pckg_title, pckg_image);
//            PackageAdaptor packages = new PackageAdaptor(this, pckg_price, pckg_season, pckg_start, pckg_end, agent_contact, pckg_title, url);
//            show_pckg.getLayoutParams().height = 200;
            show_pckg.setAdapter(packages);

            show_pckg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),"You Clicked "+result.get(position),Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}