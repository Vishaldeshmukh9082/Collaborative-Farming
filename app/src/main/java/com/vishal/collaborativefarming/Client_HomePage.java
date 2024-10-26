package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class Client_HomePage extends AppCompatActivity {
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

        addfeild.setOnClickListener(view -> {
            Intent intent=new Intent(Client_HomePage.this,Add_FeildPage.class);
            startActivity(intent);
        });
        profile.setOnClickListener(view -> {
            Intent intent=new Intent(Client_HomePage.this,Client_ProfilePage.class);
            startActivity(intent);
        });
        viewapplication.setOnClickListener(view -> {
            Intent intent=new Intent(Client_HomePage.this,View_ApplicationPage.class);
            startActivity(intent);
        });
        paperwork.setOnClickListener(view -> {
            Intent intent=new Intent(Client_HomePage.this,PaperWork_Page.class);
            startActivity(intent);
        });
        companys.setOnClickListener(view -> {
            Intent intent=new Intent(Client_HomePage.this,View_CompaniesPage.class);
            startActivity(intent);
        });


    }

//    @Override
//    public void onClick(View view) {
//        Intent i;
//        switch(view.getId()){
//            case R.id.addfeildcard:
//                i=
//                break;
//            case R.id.profilecard:
//                i=new Intent(this, Client_ProfilePage.class);
//                startActivity(i);
//                break;
//            case R.id.viewapplicationcard:
//                i=new Intent(this,View_ApplicationPage.class);
//                startActivity(i);
//                break;
//            case R.id.paperworkcard:
//                //Toast.makeText(this, "Paper Work Clicked", Toast.LENGTH_SHORT).show();
//                i=new Intent(this, PaperWork_Page.class);
//                startActivity(i);
//                break;
//            case R.id.companyscard:
//                i=new Intent(this, View_CompaniesPage.class);
//                //Toast.makeText(this, "Companys option Clicked", Toast.LENGTH_SHORT).show();
//                startActivity(i);
//                break;
//        }
//    }

}