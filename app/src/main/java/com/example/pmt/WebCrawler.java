/* package com.example.pmt;

import android.content.Context;
import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WebCrawler {
    interface CrawlingCall{
        void onPageCrawlingDone();
        void onPageCrawlingFail(String Url, int errorCode);
        void onCrawlingDone();
    }

    private Context mContext;
    private HashSet<String> crawled_url;
    BlockingQueue<String> uncrawled_url;
    CrawlingCall callback; // Callback interface object to notify UI
    Object lock; // For sync of crawled and yet to crawl url lists
    public WebCrawler(Context ctx, CrawlingCall callback){
        this.mContext = ctx;
        this.callback = callback;
        crawled_url = new HashSet<>();
        uncrawled_url = new LinkedBlockingQueue<>();
        lock = new Object();
    }
}
*/