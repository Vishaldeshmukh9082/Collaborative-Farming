package com.vishal.collaborativefarming.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishal.collaborativefarming.R;
import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Feild {
    Connection conn;
    Context context;
    private String surveyno;
    private String village;
    private String taluka;
    private String district;
    private String feildarea;
    private String description;
    private String image;
    private String userid;
    LayoutInflater inflater;


    public Feild(){}


    public Feild(String surveyno, String village, String taluka, String district, String feildarea, String description, String image, String userid) {
        this.surveyno = surveyno;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.feildarea = feildarea;
        this.description = description;
        this.image = image;
        this.userid = userid;
    }

    public int addFeild(){
        DBHelper db=new DBHelper();
        conn=db.connectionclass();
        if(conn!=null) {
            try {
                String query = "insert into feilds (surveyno,village,taluka,district,areaoffeild,description,image,userid) values ('" + surveyno + "','" + village + "','" + taluka + "','" + district + "','" + feildarea + "','" + description + "','" + image + "','" + userid + "');";
                PreparedStatement statement = conn.prepareStatement(query);
                int rs=statement.executeUpdate();
                if(rs>0){
                    Log.d("Inserted","new Feild Added");
                    return 1;
                }
                else{
                    Log.d("Inserted","no Feild inserted");
                }
            }catch (Exception ex){
                Log.e("Error",ex.toString());
            }
        }
        else {
            Log.d("Connection","NotConnected");
        }
        return 0;
    }


}
