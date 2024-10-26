package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        //used to hide title bar
         setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        button1=findViewById(R.id.CompanysBtn);
        button.setOnClickListener(view -> {
           Intent intent=new Intent(MainActivity.this,Client_Login.class);
           startActivity(intent);
        });


        button1.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, Company_Login.class);
            startActivity(intent);
            //Toast.makeText(MainActivity.this, "Companys Clicked", Toast.LENGTH_SHORT).show();
        });
    }

}