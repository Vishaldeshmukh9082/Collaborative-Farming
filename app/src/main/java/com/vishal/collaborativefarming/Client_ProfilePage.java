package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.collaborativefarming.model.User_Session;

public class Client_ProfilePage extends AppCompatActivity {

    TextView textView;
    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_page);

        butt=findViewById(R.id.logout_btn);
        textView=findViewById(R.id.textView10);

        SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
        String check=shref.getString("uid",null);
        textView.setText(check.toString());

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getSharedPreferences("ulogin",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("uid", null);
                editor.apply();
                Intent intent=new Intent(Client_ProfilePage.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}