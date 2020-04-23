package com.example.pmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class VisaInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_information);
        try{
            GetKoreanVisa connectVisa = new GetKoreanVisa();
            connectVisa.execute();

            final ArrayAdapter visaAdaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, connectVisa.get());
            ListView listview = findViewById(R.id.listView);
            listview.setAdapter(visaAdaptor);

            ArrayList<String> insert = connectVisa.get();
            String result = "";
            for (int i = 0 ; i < insert.size();i++){
                if(!insert.get(i).equals("")){
                    result = result + insert.get(i) + "\n";
                }
            }
        }catch (Exception e){
            Log.e("Exception", e.getMessage());
        }

    }
}
