package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_FeildPage extends AppCompatActivity {
    EditText villagename,taluka,district,surveyno,feildarea,description;
    Button addfeildbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feild_page);

        villagename=findViewById(R.id.villagename);
        taluka=findViewById(R.id.talukaname);
        district=findViewById(R.id.districtname);
        surveyno=findViewById(R.id.feildsurveyno);
        description=findViewById(R.id.feilddescription);

        addfeildbtn=findViewById(R.id.add_feild_btn);
        addfeildbtn.setOnClickListener(view -> {

        });
    }
}