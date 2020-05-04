package com.example.pmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.graphics.Bitmap;
import android.widget.Toast;
import java.util.ArrayList;

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
            //connect webserver to get result
            GetPackages pckg_input = new GetPackages(this, show_pckg);
            pckg_input.execute(inputted_season, "IDR " + inputted_budget);

            //get package result
            ArrayList<ArrayList> result = pckg_input.get();
            ArrayList<String> pckg_price = result.get(0);
            ArrayList<String> pckg_season = result.get(1);
            ArrayList<String> pckg_start = result.get(2);
            ArrayList<String> pckg_end = result.get(3);
            ArrayList<String> agent_contact = result.get(4);
            ArrayList<String> pckg_title = result.get(5);
            ArrayList<Bitmap> pckg_image = result.get(6);
            ArrayList<String> pckg_id = result.get(7);

            //insert package result to package adaptor
            PackageAdaptor packages = new PackageAdaptor(this, pckg_price, pckg_season, pckg_start, pckg_end, agent_contact, pckg_title, pckg_image, pckg_id);
            show_pckg.setAdapter(packages);

            show_pckg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(global.checkState.get(position)){
                        global.clickAmount -= 1;
                        packages.updateCheck(position);
                        //choose-choose package (delete data from arraylist)
                        if(global.id1 == pckg_id.get(position)){
                            global.id1 = "";
                            global.image1 = null;
                        }else if(global.id2 == pckg_id.get(position)){
                            global.id2 = "";
                            global.image2 = null;
                        }
                    }else{
                        if(global.clickAmount < 2){
                            packages.updateCheck(position);
                            global.clickAmount += 1;

                            //add data to arraylist in global class
                            if(global.id1 == ""){
                                global.id1 = pckg_id.get(position);
                                global.image1 = pckg_image.get(position);
                            }else if(global.id2 == ""){
                                global.id2 = pckg_id.get(position);
                                global.image2  = pckg_image.get(position);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Compare limit reached",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compareClick(View v){
        //handle the compare button (will be directed to comparison page)
        Intent compare = new Intent(this, compare.class);
        startActivity(compare);
    }
}