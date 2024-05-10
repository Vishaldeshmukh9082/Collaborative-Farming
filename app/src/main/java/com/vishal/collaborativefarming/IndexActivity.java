package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.mysql.jdbc.BlobFromLocator;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
                String check=shref.getString("uid",null);
                Intent intent;

                if(check!=null){
                    Log.d("Message",check);
                    intent=new Intent(IndexActivity.this,Client_HomePage.class);
                }else{
                    intent=new Intent(IndexActivity.this,MainActivity.class);
                }
                startActivity(intent);
            }
        },400);

    }
}