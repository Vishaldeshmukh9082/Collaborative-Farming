package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.collaborativefarming.database.DBHelper;
import com.vishal.collaborativefarming.model.User;
import com.vishal.collaborativefarming.model.User_Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class Client_Login extends AppCompatActivity {
    Connection conn;
    TextView textview;
    EditText login_id,login_pass;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide(); //used to hide title bar
        setContentView(R.layout.activity_client_login);

        //registraton link
        textview = findViewById(R.id.textView7);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Client_Login.this,Client_Register.class);
                startActivity(intent);
            }
        });


       //User_Session user_session=new User_Session(Client_Login.this);
        //login
        login_id = findViewById(R.id.txt_loginid);
        login_pass = findViewById(R.id.txt_loginpass);
        login_btn=findViewById(R.id.login_btn);

        login_btn.setOnClickListener(view -> {

            //User user=new User(login_id.getText().toString(),login_pass.getText().toString());
            DBHelper db=new DBHelper();
            String email=login_id.getText().toString();
            String password=login_pass.getText().toString();
            int id;

            conn=db.connectionclass();
            try {
                if (conn != null) {

                    String query="select * from user where email='"+email+"' and password='"+password+"';";

                    PreparedStatement stmt=conn.prepareStatement(query);

                    ResultSet rs=stmt.executeQuery();
                    if(rs.next()) {
                         id = (int) rs.getLong(1);

                        SharedPreferences pref=getSharedPreferences("ulogin",MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("uid", String.valueOf(id));
                        editor.apply();

                        moveToHome();
                        Log.d("Message", "Welcome");
                    }
                    else{
                        Toast.makeText(Client_Login.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception ex){
                Log.e("Error",ex.toString());
            }


        });


    }


    private void moveToHome() {
        Intent intent = new Intent(Client_Login.this, Client_HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}