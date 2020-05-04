package com.example.pmt;

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {
    Connection connect;

    public DBConnect(){
        try{
            //database connection
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/planmytrip", "user", "user");
        }catch (Exception e){
            connect =  null;
        }
    }

    public ArrayList getVisa(){
        //connect webserver to get visa information
        ArrayList<String> listVisa = new ArrayList<String>();

        try{
            //get visa information from database
            String query = "select * from korean_visa";
            Statement execQuery = connect.createStatement();
            ResultSet queryResult = execQuery.executeQuery(query);

            while(queryResult.next()){
                listVisa.add(queryResult.getString("data_name"));
                listVisa.add(queryResult.getString("data_value"));
            }
        }catch (Exception e){
            Log.e("Exception", e.getMessage());
        }
        return listVisa;
    }
}
