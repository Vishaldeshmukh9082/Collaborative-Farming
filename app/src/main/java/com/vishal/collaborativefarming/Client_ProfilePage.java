package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vishal.collaborativefarming.database.DBHelper;
import com.vishal.collaborativefarming.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Client_ProfilePage extends AppCompatActivity {

    TextView contactno,UserNamet,UserNamet1,emailidtt,addressa;
    Button butt;
    Connection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile_page);

        butt=findViewById(R.id.logout_btn);
        contactno=findViewById(R.id.conumber);
        UserNamet=findViewById(R.id.UserNamet);
        emailidtt=findViewById(R.id.emailidtt);
        addressa=findViewById(R.id.addressa);
        UserNamet1=findViewById(R.id.UserNamet1);


        SharedPreferences shref=getSharedPreferences("ulogin",MODE_PRIVATE);
        String uid=shref.getString("uid",null);
        DBHelper db=new DBHelper();
        conn=db.connectionclass();
        try{
            if (conn != null) {
                String query = "select * from user where uid='" + uid + "';";
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    UserNamet.setText( rs.getString(3));
                    UserNamet1.setText( rs.getString(3));
                    contactno.setText(rs.getString(4));
                    emailidtt.setText( rs.getString(2));
                    addressa.setText( rs.getString(6));

                }
            }
        }catch (Exception ex){
            Log.d("connection",ex.toString());
        }


        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getSharedPreferences("ulogin",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("uid", null);
                editor.apply();
                Intent intent=new Intent(Client_ProfilePage.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}