package com.example.pmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class VisaInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_information);
        try{
            GetKoreanVisa connectVisa = new GetKoreanVisa();

//            ArrayList visaResult = connectVisa.execute().get();
            connectVisa.execute();

            final ArrayAdapter visaAdaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, connectVisa.get());
            GridView visaGrid = findViewById(R.id.gridVisa);
            visaGrid.setAdapter(visaAdaptor);
        }catch (Exception e){
            Log.e("Exception", e.getMessage());
        }

    }
}
