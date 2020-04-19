package com.example.pmt;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

public class PackageAdaptor extends BaseAdapter{
    private Context c;

    private ArrayList<String> pckg_price;
    private ArrayList<String> pckg_season;
    private ArrayList<String> pckg_start;
    private ArrayList<String> pckg_end;
    private ArrayList<String> agent_contact;
    private ArrayList<String> pckg_title;
    private ArrayList<String> pckg_img_url;
    private ArrayList<Bitmap> pckg_image;

    public PackageAdaptor(Context c, ArrayList<String> price, ArrayList<String> season, ArrayList<String> start, ArrayList<String> end, ArrayList<String> contact, ArrayList<String> title, ArrayList<Bitmap> image){
//public PackageAdaptor(Context c, ArrayList<String> price, ArrayList<String> season, ArrayList<String> start, ArrayList<String> end, ArrayList<String> contact, ArrayList<String> title,  ArrayList<String> img_url){
        this.c = c;
        pckg_price = price;
        pckg_season = season;
        pckg_start = start;
        pckg_end = end;
        agent_contact = contact;
        pckg_title = title;
//        pckg_img_url = img_url;
        pckg_image = image;
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
        ImageView image = pckg_grid.findViewById((R.id.imageView2));
        TextView title = pckg_grid.findViewById(R.id.textView);
        TextView price = pckg_grid.findViewById(R.id.textView7);
        TextView season = pckg_grid.findViewById(R.id.textView4);
        TextView start = pckg_grid.findViewById(R.id.textView5);
        TextView end = pckg_grid.findViewById(R.id.textView6);
        TextView contact = pckg_grid.findViewById(R.id.textView8);

        image.setImageBitmap(pckg_image.get(position));
        title.setText(pckg_title.get(position));
        price.setText(pckg_price.get(position));
        season.setText(pckg_season.get(position));
        start.setText(pckg_start.get(position));
        end.setText(pckg_end.get(position));
        contact.setText(agent_contact.get(position));

//        pckg_grid.getLayoutParams().height = 100;

//        pckg_grid.setLayoutParams(50,50);
        return pckg_grid;
    }
}