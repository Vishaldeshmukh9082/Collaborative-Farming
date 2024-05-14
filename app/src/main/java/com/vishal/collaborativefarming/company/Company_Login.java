package com.vishal.collaborativefarming.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishal.collaborativefarming.R;
import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Company_Login extends AppCompatActivity {

    EditText email,password;
    Button loginbtn;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        SharedPreferences pref=getSharedPreferences("companylogin",MODE_PRIVATE);


        email=findViewById(R.id.txt_cloginid);
        password=findViewById(R.id.txt_cloginpass);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                company_login();

            }

            private void company_login() {

                DBHelper db=new DBHelper();
                conn=db.connectionclass();

                try{
                    if(conn!=null){
                        String query ="select * from company where email="+email+"and password="+password+";";
                        PreparedStatement statement=conn.prepareStatement(query);
                        ResultSet rs=statement.executeQuery();
                        if(rs!=null){
                            Toast.makeText(Company_Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=pref.edit();
                            editor.putString("cid","email");
                            editor.apply();

                        }else{
                            Toast.makeText(Company_Login.this, "Please Check your Credentials!!", Toast.LENGTH_SHORT).show();
                        }

                        conn.close();
                    }
                }catch(Exception e){

                }
            }
        });


    }
}