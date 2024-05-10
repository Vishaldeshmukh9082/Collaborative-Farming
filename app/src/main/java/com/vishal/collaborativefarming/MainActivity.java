package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.vishal.collaborativefarming.model.User_Session;

public class MainActivity extends AppCompatActivity {

    Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //used to hide title bar
         setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,Client_Login.class);
               startActivity(intent);
            }
        });

        button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,Add_FeildPage.class);
//                startActivity(intent);
                Toast.makeText(MainActivity.this, "Companys Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

}