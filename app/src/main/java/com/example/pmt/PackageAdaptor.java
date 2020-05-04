package com.example.pmt;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class PackageAdaptor extends BaseAdapter{
    private Context c;

    private ArrayList<String> pckg_price;
    private ArrayList<String> pckg_season;
    private ArrayList<String> pckg_start;
    private ArrayList<String> pckg_end;
    private ArrayList<String> agent_contact;
    private ArrayList<String> pckg_title;
    private ArrayList<Bitmap> pckg_image;
    private ArrayList<String> pckg_id;

    //insert data to package adaptor for packages
    public PackageAdaptor(Context c, ArrayList<String> price, ArrayList<String> season, ArrayList<String> start, ArrayList<String> end, ArrayList<String> contact, ArrayList<String> title, ArrayList<Bitmap> image, ArrayList<String> id){
//public PackageAdaptor(Context c, ArrayList<String> price, ArrayList<String> season, ArrayList<String> start, ArrayList<String> end, ArrayList<String> contact, ArrayList<String> title,  ArrayList<String> img_url){
        this.c = c;
        pckg_price = price;
        pckg_season = season;
        pckg_start = start;
        pckg_end = end;
        agent_contact = contact;
        pckg_title = title;
        pckg_image = image;
        pckg_id = id;

        //handles the maximum choosen packages to be compared
        global.checkState = new ArrayList<Boolean>();
        for(int i = 0; i < pckg_title.size(); i++){
            global.checkState.add(false);
        }

        global.clickAmount = 0;
        //Collections.fill(checkState,Boolean.FALSE);
    }

    public void updateCheck(int updateIndex){
        global.checkState.set(updateIndex, !global.checkState.get(updateIndex));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pckg_price.size();
    }

    @Override
    public Object getItem(int position) {
        return pckg_price.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View pckg_grid = convertView;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pckg_grid = inflater.inflate(R.layout.package_grid, null);
        ImageView checkBox = pckg_grid.findViewById((R.id.imageView));
        ImageView image = pckg_grid.findViewById((R.id.imageView2));
        TextView title = pckg_grid.findViewById(R.id.textView);
        TextView price = pckg_grid.findViewById(R.id.textView7);
        TextView season = pckg_grid.findViewById(R.id.textView4);
        TextView start = pckg_grid.findViewById(R.id.textView5);
        TextView end = pckg_grid.findViewById(R.id.textView6);
        TextView contact = pckg_grid.findViewById(R.id.textView8);
        Button details = pckg_grid.findViewById(R.id.btn_details);

        //handles the position of each data to be shown on the app
        image.setImageBitmap(pckg_image.get(position));
        title.setText(pckg_title.get(position));
        price.setText(pckg_price.get(position));
        season.setText(pckg_season.get(position));
        start.setText(pckg_start.get(position));
        end.setText(pckg_end.get(position));
        contact.setText(agent_contact.get(position));

        //handle button click to packages detail for each packages
        details.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details_pckg = new Intent(c,PackageDetails.class);
                details_pckg.putExtra("id", pckg_id.get(position));
                c.startActivity(details_pckg);
            }
        });

        //handles the checkbox image for compare packages
        if(global.checkState.get(position)){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.INVISIBLE);
        }
        return pckg_grid;
    }
}