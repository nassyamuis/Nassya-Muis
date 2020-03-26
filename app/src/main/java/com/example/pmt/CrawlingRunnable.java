/*package com.example.pmt;

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

class CrawlingRunnable implements Runnable{

    WebCrawler.CrawlingCall mCallback;
    String mUrl;

    public CrawlingRunnable(WebCrawler.CrawlingCall callback, String Url){
        this.mCallback = callback;
        this.mUrl = Url;
    }

    public void run() {
        String page_content = retrieveHtmlContent(mUrl);

        if (!TextUtils.isEmpty(page_content.toString())){
            // Start and jsoup library used to filter urls from html body
            Document docs = Jsoup.parse(page_content.toString());
            Elements links = docs.select("a[href]");
            for (Element link:links){
                String extracted_links = link.attr("href");
                if (!TextUtils.isEmpty(extracted_links)){
                    synchronized (lock){
                        if(!crawled_url.contains(extracted_links))
                            uncrwaled_url.add(extracted_links);
                    }
                }
            } //jsoup end
        }
    }

    private String retrieveHtmlContent(String Url){
        URL http_url = null;
        try{
            http_url = new URL(Url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        int response_code = HttpStatus.SC_OK;
        StringBuilder page_content = new StringBuilder();
        try{
            if(http_url != null){
                HttpURLConnection connect = (HttpURLConnection) http_url.openConnection();
                connect.setConnectTimeout(5000);
                connect.setReadTimeout(5000);
                response_code = connect.getResponseCode();
                if(response_code != HttpStatus.SC_OK){
                    throw new IllegalAccessException("connection failed");
                }
                BufferedReader buf_read = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String line = null;
                while((line = buf_read.readLine()) != null){
                    page_content.append(line);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            mCallback.onPageCrawlingFail(Url, -1);
        }catch (IllegalAccessException e){
            e.printStackTrace();
            mCallback.onPageCrawlingFail(Url, response_code);
        }
        return page_content.toString();
    }
}
*/