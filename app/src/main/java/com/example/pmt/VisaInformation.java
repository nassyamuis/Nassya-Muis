package com.example.pmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class VisaInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_information);
        try{
            GetKoreanVisa connectVisa = new GetKoreanVisa();
            connectVisa.execute();

            final ArrayAdapter visaAdaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, connectVisa.get());
//            TextView visaText = findViewById(R.id.text1);
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.connectVisa.get(),android.R.layout.simple_list_item_1);

            ListView listview = findViewById(R.id.listView);
            listview.setAdapter(visaAdaptor);

            ArrayList<String> tulis = connectVisa.get();
            String result = "";
            for (int i =0 ; i<tulis.size();i++){
                if(!tulis.get(i).equals("")){
                    result = result + tulis.get(i) + "\n";
                }
            }
//            visaText.setText(result);
//            GridView visaGrid = findViewById(R.id.gridVisa);
//            visaGrid.setAdapter(visaAdaptor);
        }catch (Exception e){
            Log.e("Exception", e.getMessage());
        }

    }
}
