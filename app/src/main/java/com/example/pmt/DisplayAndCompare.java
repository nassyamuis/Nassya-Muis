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
            GetPackages pckg_input = new GetPackages(this, show_pckg);
            pckg_input.execute(inputted_season, "IDR " + inputted_budget);

            ArrayList<ArrayList> result = pckg_input.get();
            ArrayList<String> pckg_price = result.get(0);
            ArrayList<String> pckg_season = result.get(1);
            ArrayList<String> pckg_start = result.get(2);
            ArrayList<String> pckg_end = result.get(3);
            ArrayList<String> agent_contact = result.get(4);
            ArrayList<String> pckg_title = result.get(5);
            ArrayList<Bitmap> pckg_image = result.get(6);
            ArrayList<String> pckg_id = result.get(7);

            PackageAdaptor packages = new PackageAdaptor(this, pckg_price, pckg_season, pckg_start, pckg_end, agent_contact, pckg_title, pckg_image, pckg_id);
            show_pckg.setAdapter(packages);

            show_pckg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),"You Clicked "+result.get(position),Toast.LENGTH_SHORT).show();
                    if(global.checkState.get(position)){
                        global.clickAmount -= 1;
                        packages.updateCheck(position);
                        //edit cancel choose (hapus data dri arraylist)
                    }else{
                        if(global.clickAmount < 2){
                            packages.updateCheck(position);
                            global.clickAmount += 1;

                            //edit tambah data ke arraylist di global
                            global.compare_title.add(pckg_title.get(position));
                            global.compare_price.add(pckg_price.get(position));
                            global.compare_start.add(pckg_start.get(position));
                            global.compare_end.add(pckg_end.get(position));
                            //global.compare_itinerary.add(.get(position));
                        }else{
                            Toast.makeText(getApplicationContext(),"Compare limit reached",Toast.LENGTH_SHORT).show();
                        }
                    }
                    packages.updateCheck(position);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}