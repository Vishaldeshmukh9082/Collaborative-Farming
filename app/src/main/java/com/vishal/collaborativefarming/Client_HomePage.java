package com.vishal.collaborativefarming;

//import static com.vishal.collaborativefarming.R.id.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Client_HomePage extends AppCompatActivity {
    CardView addfeild,profile,viewapplication,paperwork,companys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);

        addfeild= findViewById(R.id.addfeildcard);
        profile=findViewById(R.id.profilecard);

        addfeild.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Client_HomePage.this,Add_FeildPage.class);
                startActivity(intent);
                Toast.makeText(Client_HomePage.this, "add feild clicked", Toast.LENGTH_SHORT).show();
            }
        });
//
        profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Client_HomePage.this,Client_ProfilePage.class);
                startActivity(intent);
            }
        });

    }

}