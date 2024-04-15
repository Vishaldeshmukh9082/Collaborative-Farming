package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Client_Login extends AppCompatActivity {
    TextView textview;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide(); //used to hide title bar
        setContentView(R.layout.activity_client_login);

        textview = findViewById(R.id.textView7);

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Client_Login.this,Client_Register.class);
                startActivity(intent);
            }
        });

        button=findViewById(R.id.login_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Client_Login.this,Client_HomePage.class);
                startActivity(intent2);
            }
        });

    }
}