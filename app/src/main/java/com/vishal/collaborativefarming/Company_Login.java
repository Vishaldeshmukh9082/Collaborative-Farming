package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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



        email=findViewById(R.id.txt_cloginid);
        password=findViewById(R.id.txt_cloginpass);
        loginbtn=findViewById(R.id.complogin_btn);

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
                        String query ="select * from company where email='"+email.getText()+"' and password='"+password.getText()+"';";
                        PreparedStatement statement=conn.prepareStatement(query);
                        ResultSet rs=statement.executeQuery();
                        if(rs.next()){
                            Toast.makeText(Company_Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                            SharedPreferences pref=getSharedPreferences("companylogin",MODE_PRIVATE);
                            SharedPreferences.Editor editor=pref.edit();
                            editor.putString("cid",email.getText().toString());
                            editor.apply();
                            moveToHome();

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
    private void moveToHome() {
        Intent intent = new Intent(Company_Login.this, Company_HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}