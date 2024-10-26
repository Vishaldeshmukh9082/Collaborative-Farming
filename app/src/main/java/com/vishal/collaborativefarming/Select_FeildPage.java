package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Select_FeildPage extends AppCompatActivity {
    TextView textView;
    String em;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_feild_page);
        if(getIntent().hasExtra("companyemail")){
            em=getIntent().getStringExtra("companyemail");

            textView=findViewById(R.id.textView13);
            textView.setText(em);
        }else {
            Toast.makeText(this, "NoData", Toast.LENGTH_SHORT).show();
        }

    }
}