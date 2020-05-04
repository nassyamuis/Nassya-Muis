package com.example.pmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchPackage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_package);
    }

    public void btn_search(View search_pcg){
        //handles the budget input
        EditText budget_box = findViewById(R.id.budget);
        String inputbudget = budget_box.getText().toString();

        try{
            int budget = Integer.parseInt(inputbudget);
            //handles the season
            Spinner season_drop = findViewById(R.id.list_season);
            String inputseason = season_drop.getSelectedItem().toString();

            Intent red_display = new Intent(this, DisplayAndCompare.class);
            red_display.putExtra("budget", budget);
            red_display.putExtra("season", inputseason);
            startActivity(red_display);

        } catch (NumberFormatException a){

        }
    }
}
