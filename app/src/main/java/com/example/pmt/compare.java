package com.example.pmt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class compare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        ImageView show_image1 = findViewById(R.id.imageView3);
        ImageView show_image2 = findViewById(R.id.imageView4);
        GridView show_pckg = findViewById(R.id.pckg_comparison);

        show_image1.setImageBitmap(global.image1);
        show_image2.setImageBitmap(global.image2);
    }
}
