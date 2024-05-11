package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vishal.collaborativefarming.database.DBHelper;
import com.vishal.collaborativefarming.model.Feild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class View_ApplicationPage extends AppCompatActivity {
    Connection conn;


    RecyclerView recyclerView;
    ArrayList<String> surveyno,village,taluka,district,feildarea,description,image;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_page);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        surveyno=new ArrayList<>();
        village=new ArrayList<>();
        taluka=new ArrayList<>();
        district=new ArrayList<>();
        feildarea=new ArrayList<>();
        description=new ArrayList<>();
        image=new ArrayList<>();

        getFeilds();
        customAdapter=new CustomAdapter(View_ApplicationPage.this,surveyno,village,taluka,district,feildarea,description,image);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(View_ApplicationPage.this));
    }

    void getFeilds(){
        SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
        String id=shref.getString("uid",null);
        DBHelper db=new DBHelper();
        conn=db.connectionclass();
        if(conn!=null) {
            try {
                String query = "select * from feilds where userid='"+id+"';";
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs=statement.executeQuery();
                while(rs.next()) {
                    surveyno.add(rs.getString(2));
                    village.add(rs.getString(3));
                    taluka.add(rs.getString(4));
                    district.add(rs.getString(5));
                    feildarea.add(rs.getString(6));
                    description.add(rs.getString(7));
                    image.add(rs.getString(8));

                }

            }catch (Exception ex){
                Log.e("Error",ex.toString());
            }
        }
        else {
            Log.d("Connection","NotConnected");
        }

    }
}