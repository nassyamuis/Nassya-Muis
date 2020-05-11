package com.example.pmt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CompareAdaptor extends BaseAdapter {
    private Context c;

    private ArrayList<String> comparison;

    public CompareAdaptor(Context c, ArrayList<String> comparisondata){
        this.c = c;
        comparison = comparisondata;
    }

    @Override
    public int getCount() {
        return comparison.size();
    }

    @Override
    public Object getItem(int position) {
        return comparison.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View compare = convertView;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        compare = inflater.inflate(R.layout.compare_grid_1, null);
        TextView package1 = compare.findViewById((R.id.compare1));
        TextView package2 = compare.findViewById((R.id.compare2));
        TextView title1 = compare.findViewById(R.id.title_left);
        TextView title2 = compare.findViewById(R.id.title_right);

        String[] split = comparison.get(position).split("\\|");
        String res1, res2;

        if(position >= 6){
            String[] split2 = split[1].split("~");
            String[] split3 = split[2].split("~");

            res1 = split2[0] + "\n" + split2[1];
            res2 = split3[0] + "\n" + split3[1];
        }else{
            res1 = split[1];
            res2 = split[2];
        }

        package1.setText(res1);
        package2.setText(res2);
        title1.setText(split[0]);
        title2.setText(split[0]);

        if(split[0] == ""){
            title1.setVisibility(View.GONE);
            title2.setVisibility(View.GONE);
        }

        return compare;
    }
}
