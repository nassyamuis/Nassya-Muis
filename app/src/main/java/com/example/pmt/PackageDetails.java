package com.example.pmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

        //assign requried data to xml file
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

            //connect webserver to get the result of itinerary
            ArrayList<String> result = pckg_input.get();
            title.setText(result.get(1));
            price.setText(result.get(2));
            season.setText(result.get(3));
            start.setText(result.get(4));
            end.setText(result.get(5));
            contact.setText(result.get(6));

            //get itinerary details
            ArrayList<String> details_itinerary = result;

            for(int i = 0; i <= 6; i++){
               details_itinerary.remove(0);
            }

            //insert data to array adapter
            ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,details_itinerary);
            itinerary.setAdapter(adapter);
        }catch (Exception e){

        }
    }
}
