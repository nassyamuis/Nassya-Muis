package com.example.pmt;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class global {
    //set global variable for database
    public static String link = "http://192.168.0.8/PlanMyTrip/";

    //set global variable for comparison result page
    public static String id1 = "";
    public static String id2 = "";
    public static Bitmap image1;
    public static Bitmap image2;

    //set global variable for max compare number
    public static ArrayList<Boolean> checkState;
    public static int clickAmount = 0;
}
