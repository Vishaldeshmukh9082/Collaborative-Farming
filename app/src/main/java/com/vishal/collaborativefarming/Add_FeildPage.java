package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishal.collaborativefarming.model.Feild;
import com.vishal.collaborativefarming.model.User;

public class Add_FeildPage extends AppCompatActivity {
    EditText villagename,surveyno,feildarea,description;

    Button addfeildbtn;
    String selecteddistrict,selectedtaluka;
    ArrayAdapter<String> districtadapter, talukaadapter;
    String[] districtsel = {"satara", "kolhapur"};
    String[] satara = {"jawali", "khatav"};
    String[] kolhapur = {"panhala", "shirol"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feild_page);

        villagename=findViewById(R.id.villagename);
        surveyno=findViewById(R.id.feildsurveyno);
        feildarea=findViewById(R.id.feildarea);
        description=findViewById(R.id.feilddescription);
        String image="as";
        SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
        String id=shref.getString("uid",null);
        AutoCompleteTextView autoCompletedistrict =findViewById(R.id.district);
        AutoCompleteTextView autoCompletetaluka = findViewById(R.id.taluka);
        districtadapter = new ArrayAdapter<String>(this, R.layout.list_item1, districtsel);
        autoCompletedistrict.setAdapter(districtadapter);

        autoCompletedistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selecteddistrict = parent.getItemAtPosition(position).toString();
                if (selecteddistrict == "satara") {
                    talukaadapter = new ArrayAdapter<String>(Add_FeildPage.this, R.layout.list_item1, satara);
                    autoCompletetaluka.setAdapter(talukaadapter);

                }
                if (selecteddistrict == "kolhapur") {
                    talukaadapter = new ArrayAdapter<String>(Add_FeildPage.this, R.layout.list_item1, kolhapur);
                    autoCompletetaluka.setAdapter(talukaadapter);
                }

            }
        });
        autoCompletetaluka.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectedtaluka = parent.getItemAtPosition(position).toString();

            }
        });


        addfeildbtn=findViewById(R.id.add_feild_btn);
        addfeildbtn.setOnClickListener(view -> {
            Feild feild=new Feild(surveyno.getText().toString(),villagename.getText().toString(),selectedtaluka,selecteddistrict,feildarea.getText().toString(),description.getText().toString(),image,id);
            int result=feild.addFeild();
            if(result!=0){
                Toast.makeText(this, "Feild Added", Toast.LENGTH_SHORT).show();

                villagename.setText("");
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