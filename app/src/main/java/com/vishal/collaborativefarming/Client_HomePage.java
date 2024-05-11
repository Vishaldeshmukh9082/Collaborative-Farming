package com.vishal.collaborativefarming;

//import static com.vishal.collaborativefarming.R.id.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Client_HomePage extends AppCompatActivity implements OnClickListener {
    CardView addfeild,profile,viewapplication,paperwork,companys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);

        addfeild=  findViewById(R.id.addfeildcard);
        profile=findViewById(R.id.profilecard);
        viewapplication=findViewById(R.id.viewapplicationcard);
        paperwork=findViewById(R.id.paperworkcard);
        companys=findViewById(R.id.companyscard);

        addfeild.setOnClickListener(this);
        profile.setOnClickListener(this);
        viewapplication.setOnClickListener(this);
        paperwork.setOnClickListener(this);
        companys.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.addfeildcard:
                i=new Intent(this,Add_FeildPage.class);


                startActivity(i);
                break;
            case R.id.profilecard:
                i=new Intent(this, Client_ProfilePage.class);
                startActivity(i);
                break;
            case R.id.viewapplicationcard:
                i=new Intent(this,View_ApplicationPage.class);
                startActivity(i);
                break;
            case R.id.paperworkcard:
                Toast.makeText(this, "Paper Work Clicked", Toast.LENGTH_SHORT).show();
//                i=new Intent(this,Add_FeildPage.class);

                break;
            case R.id.companyscard:
                i=new Intent(this, View_CompaniesPage.class);
                //Toast.makeText(this, "Companys option Clicked", Toast.LENGTH_SHORT).show();
                startActivity(i);
                break;
        }
    }

}