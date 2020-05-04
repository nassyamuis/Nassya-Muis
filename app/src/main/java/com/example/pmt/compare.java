package com.example.pmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
//            ListView comparison_result1 = findViewById(R.id.comparison_1);
//            ListView comparison_result2 = findViewById(R.id.comparison_2);
//            GridView comparison_result1 = findViewById(R.id.comparison_1);
//            GridView comparison_result2 = findViewById(R.id.comparison_2);
            ListView compare_result1 = findViewById(R.id.comparison1);

            //connect webserver to get comparison result
            GetComparison get_result1 = new GetComparison(this);
            GetComparison get_result2 = new GetComparison(this);
            get_result1.execute(global.id1);
            get_result2.execute(global.id2);

            //get comparison result
            ArrayList<String> show_pckg1 = get_result1.get();
            ArrayList<String> show_pckg2 = get_result2.get();

            //get the image from database
            show_image1.setImageBitmap(global.image1);
            show_image2.setImageBitmap(global.image2);

            //insert data to each listview
            CompareAdaptor packages = new CompareAdaptor(this, show_pckg1, show_pckg2);
            compare_result1.setAdapter(packages);
//            ArrayAdapter adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,show_pckg1);
//            ArrayAdapter adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,show_pckg2);
//            comparison_result1.setAdapter(adapter1);
//            comparison_result2.setAdapter(adapter2);
        }catch (Exception e){

        }
    }
}
