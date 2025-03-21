package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
                SharedPreferences pref=getSharedPreferences("companylogin",MODE_PRIVATE);
                String user=shref.getString("uid",null);
                String company=pref.getString("cid",null);
                Intent intent;

                if(user!=null){
                    Log.d("Message",user);
                    intent=new Intent(IndexActivity.this,Client_HomePage.class);
                }else if(company!=null){
                    intent=new Intent(IndexActivity.this, Company_HomePage.class);
                }else{
                    intent=new Intent(IndexActivity.this,MainActivity.class);
                }
                startActivity(intent);
            }
        },400);

    }
}