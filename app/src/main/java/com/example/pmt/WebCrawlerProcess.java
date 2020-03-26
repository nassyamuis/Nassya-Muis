package com.example.pmt;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class WebCrawlerProcess extends AsyncTask<Object,ArrayList,ArrayList> {

    @Override
    protected ArrayList doInBackground(Object[] objects) {
        ArrayList list = new ArrayList();

        try {
            //Document docs = Jsoup.connect("https://www.kiatravels.co.id/group_tour/more_grouptour?TOUR_REGIONAL=002&CID=002&TOUR_ID=1467").get();
            Document docs = Jsoup.connect("http://www.jsoup.org/").get();
            org.jsoup.select.Elements pckg_data = docs.select("a");

            for (Element e : pckg_data) {
                list.add(e.attr("abs:href"));
            }
        }catch (Exception e){e.printStackTrace();}
            return list;
        }

    }
