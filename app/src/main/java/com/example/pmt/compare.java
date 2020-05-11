package com.example.pmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class compare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        try {
            ImageView show_image1 = findViewById(R.id.imageView3);
            ImageView show_image2 = findViewById(R.id.imageView4);
            ImageView recomm_left = findViewById(R.id.recommended_left);
            ImageView recomm_right = findViewById(R.id.recommended_right);
            GridView comparison_result1 = findViewById(R.id.comparison_1);

            //connect webserver to get comparison result
            GetComparison get_result1 = new GetComparison(this);
            GetComparison get_result2 = new GetComparison(this);
            get_result1.execute(global.id1);
            get_result2.execute(global.id2);

            //get comparison result
            ArrayList<String> show_pckg1 = get_result1.get();
            ArrayList<String> show_pckg2 = get_result2.get();
            ArrayList<String> data_result = new ArrayList<>();

            //get the image from database
            show_image1.setImageBitmap(global.image1);
            show_image2.setImageBitmap(global.image2);

            String[] split1 = show_pckg1.get(1).split(" ");
            String[] split2 = show_pckg2.get(1).split(" ");

            String priceConvert1 = split1[1].replace(".","");
            String priceConvert2 = split2[1].replace(".","");

            double price1;
            double price2;

            price1 = Double.parseDouble(priceConvert1);
            price2 = Double.parseDouble(priceConvert2);

            //compare price
            if(price1 < price2){
                recomm_left.setVisibility(View.VISIBLE);
            }else if (price2 < price1){
                recomm_right.setVisibility(View.VISIBLE);
            }

            //jumlah data 1 lebih besar sama dengan data2
            if(show_pckg1.size() >= show_pckg2.size()){
                for(int i = 0; i < show_pckg1.size(); i++){
                    String data = "";
                    if(i == 0){
                        data = "Package|";
                    }else if(i == 1){
                        data = "Price|";
                    }else if(i == 2){
                        data = "Season|";
                    }else if(i == 3){
                        data = "Depart Date|";
                    }else if(i == 4){
                        data = "Arrival Date|";
                    }else if(i == 5){
                        data = "Agent Contact|";
                    }else if(i == 6){
                        data = "Itinerary|";
                    }else{
                        data="|";
                    }
                    data += show_pckg1.get(i) + "|";

                    if(i < show_pckg2.size()){
                        data += show_pckg2.get(i);
                    }else{
                        data += " ~ ";
                    }

                    data_result.add(data);
                }
            }else{ //data 2 lebih besar atau sama dengan data1
                for(int i = 0; i < show_pckg2.size(); i++){
                    String data = "";
                    if(i == 0){
                        data = "Package|";
                    }else if(i == 1){
                        data = "Price|";
                    }else if(i == 2){
                        data = "Season|";
                    }else if(i == 3){
                        data = "Depart Date|";
                    }else if(i == 4){
                        data = "Arrival Date|";
                    }else if(i == 5){
                        data = "Agent Contact|";
                    }else if(i == 6){
                        data = "Itinerary|";
                    }else {
                        data = "|";
                    }

                    data += show_pckg2.get(i) + "|";

                    if(i < show_pckg1.size()){
                        data += show_pckg1.get(i);
                    }else{
                        data += " ~ ";
                    }
                    data_result.add(data);
                }
            }

            //insert data to each textview
            CompareAdaptor packages = new CompareAdaptor(this, data_result);
            comparison_result1.setAdapter(packages);

        }catch (Exception e){

        }
    }
}
