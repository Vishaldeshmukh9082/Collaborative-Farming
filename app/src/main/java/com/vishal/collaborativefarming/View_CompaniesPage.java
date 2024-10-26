package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

public class View_CompaniesPage extends AppCompatActivity {
    String[] companyemail={"leslischips9082@gmail.com","butterflypvtltd@gmail.com","pratsinghkarkhana@gmail.com","kohinooroil@gmail.com","tejumasala@gmail.com"};

    String[] companyname={"leslis Chips Pvt.Ltd", "Butterfly Pvt.Ltd","Pratsingh Suger karkhana Ltd","Kohinoor Refined Oil Pvt.Ltd","Teju Masala Pvt.Ltd"};
    int[] copmanyimage={R.drawable.chipscompany,R.drawable.conflowercompany,R.drawable.sugarcompanyimg,R.drawable.soyabeancompany,R.drawable.masalacompany};
    String[] location={"MIDC Khandala, satra","MIDC Shirval, satara","Bhuij, satara","Koregav, satara","Powai Naka,satara"};
    String[] companydescription={"AUEVSS Limited is an Indian potato chip processing company by potato growers",
            "Pure high quality conflour and Icing Sugar with export quality pharama grade.",
            "The company has a strong focus on innovation and has developed severalproducts like low glycemic sugar, organic sugar,",
            " Kohinoor Foods Ltd. has gone past numerous milestones becoming a global food giant. And it looks forward with hope and glory to scale greater heights ",
            " We are India's largest selling spice brand with more than 45 varieties of spices and masalas to offer. "};
    RecyclerView recyclerView;
    CompaniesAdapter companiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_companies);

        recyclerView=findViewById(R.id.Companiesrecycleview);

        companiesAdapter=new CompaniesAdapter(View_CompaniesPage.this,this,companyname,copmanyimage,location,companydescription,companyemail);
        recyclerView.setAdapter(companiesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(View_CompaniesPage.this));
    }
}