package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.*;

public class test_activity extends AppCompatActivity {

    Connection conn;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button=findViewById(R.id.testbtn);
        textView=findViewById(R.id.testview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbcon=new DBHelper();
                conn=dbcon.connectionclass();

                if(conn!=null){
                    textView.setText("connected");

                }else{
                    textView.setText("not conncted");
                }
            }
        });
    }
}