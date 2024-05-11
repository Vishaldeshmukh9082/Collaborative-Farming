package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishal.collaborativefarming.model.Feild;
import com.vishal.collaborativefarming.model.User;

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
        feildarea=findViewById(R.id.feildarea);
        description=findViewById(R.id.feilddescription);
        String image="as";
        SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
        String id=shref.getString("uid",null);


        addfeildbtn=findViewById(R.id.add_feild_btn);
        addfeildbtn.setOnClickListener(view -> {
            Feild feild=new Feild(surveyno.getText().toString(),villagename.getText().toString(),taluka.getText().toString(),district.getText().toString(),feildarea.getText().toString(),description.getText().toString(),image,id);
            int result=feild.addFeild();
            if(result!=0){
                Toast.makeText(this, "Feild Added", Toast.LENGTH_SHORT).show();

                villagename.setText("");
                taluka.setText("");
                district.setText("");
                surveyno.setText("");
                feildarea.setText("");
                description.setText("");
                finish();
            }else{
                Toast.makeText(this, "Check Your Feild details", Toast.LENGTH_SHORT).show();

            }

        });
    }
}