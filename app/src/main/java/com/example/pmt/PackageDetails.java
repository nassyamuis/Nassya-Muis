package com.example.pmt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PackageDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);

        Intent details_pckg = getIntent();
        String packagesID = details_pckg.getStringExtra("id");

        TextView title = findViewById(R.id.title_details);
        TextView price = findViewById(R.id.price_details);
        TextView season = findViewById(R.id.season_details);
        TextView start = findViewById(R.id.start_details);
        TextView end = findViewById(R.id.end_details);
        TextView contact = findViewById(R.id.agent_details);
        ListView itinerary = findViewById(R.id.itinerary_details);


        try {
            getData pckg_input = new getData(this);
            pckg_input.execute(packagesID);

            ArrayList<String> result = pckg_input.get();
            title.setText(result.get(0));
            price.setText(result.get(1));
            season.setText(result.get(2));
            start.setText(result.get(3));
            end.setText(result.get(4));
            contact.setText(result.get(5));

            ArrayList<String> details_itinerary = result;

            for(int i=0; i<6; i++){
               details_itinerary.remove(0);
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,details_itinerary);
            itinerary.setAdapter(adapter);
        }catch (Exception e){

        }
    }
}
