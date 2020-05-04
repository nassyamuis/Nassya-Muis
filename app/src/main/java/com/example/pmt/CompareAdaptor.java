package com.example.pmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CompareAdaptor extends BaseAdapter {
    private Context c;

    private ArrayList<String> comparison_1;
    private ArrayList<String> comparison_2;

    public CompareAdaptor(Context c, ArrayList<String> package1, ArrayList<String> package2){
        this.c = c;
        comparison_1 = package1;
        comparison_2 = package2;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View compare = convertView;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        compare = inflater.inflate(R.layout.compare_grid, null);
        ListView package1 = compare.findViewById((R.id.comparison1));
        ListView package2 = compare.findViewById((R.id.comparison2));

        ArrayAdapter package_1 = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, comparison_1);
        ArrayAdapter package_2 = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, comparison_2);

        package1.setAdapter(package_1);
        package2.setAdapter(package_2);

        return compare;
    }
}
