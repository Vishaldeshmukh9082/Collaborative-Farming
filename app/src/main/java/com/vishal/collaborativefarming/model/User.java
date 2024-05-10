package com.vishal.collaborativefarming.model;

import android.util.Log;

import com.vishal.collaborativefarming.Client_Login;
import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    Connection conn;

    private int id;
    private String name;
    private String contactno;
    private String email;
    private String gender;
    private String address;
    private String password;

    public User(String email,String password){
        this.email=email;
        this.password=password;
    }

    public User( String name, String contactno, String email, String gender, String address, String password) {

        this.name = name;
        this.contactno = contactno;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int register(){
        DBHelper db=new DBHelper();

        conn=db.connectionclass();
        try {
            if (conn != null) {

                String query="insert into user (name,contactno,email,gender,address,password) values('"+name+"','"+contactno+"','"+email+"','"+gender+"','"+address+"','"+password+"')";
                PreparedStatement stmt=conn.prepareStatement(query);
                int rs=stmt.executeUpdate();
                if(rs>0){
                    Log.d("Inserted","new record insert");
                    return 1;
                }
                else{
                    Log.d("Inserted","no record inserted");
                }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }

        return 0;
    }

    public int login(){
        DBHelper db=new DBHelper();

        conn=db.connectionclass();
        try {
            if (conn != null) {

                String query="select * from user where email='"+email+"' and password='"+password+"';";

                PreparedStatement stmt=conn.prepareStatement(query);

                ResultSet rs=stmt.executeQuery();

                if(rs.next()){
                    Log.d("Inserted",email);
                    return 1;
                }
                else{
                    Log.d("Inserted","Invalid username and password");
                }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }

        return 0;

    }

}
