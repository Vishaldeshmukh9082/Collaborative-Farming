package com.vishal.collaborativefarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishal.collaborativefarming.model.User;

public class Client_Register extends AppCompatActivity {
    EditText txtname,txtcontact,txtemail,txtaddress,txtpassword;
    Button register_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register);

        txtname=findViewById(R.id.txt_loginpass);
        txtcontact=findViewById(R.id.txt_contactno);
        txtemail=findViewById(R.id.txt_emailid);
        String txtgender="male";
        txtaddress=findViewById(R.id.txt_address);
        txtpassword=findViewById(R.id.txt_password);
        register_btn=findViewById(R.id.register_button);


        register_btn.setOnClickListener(view -> {
            User user=new User(txtname.getText().toString(),txtcontact.getText().toString(),txtemail.getText().toString(),txtgender,txtaddress.getText().toString(),txtpassword.getText().toString());
             int temp=user.register();
            if(temp==1){
                Toast.makeText(Client_Register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Client_Login.class);
                startActivity(intent);
            }else {

            }
        });

    }
}