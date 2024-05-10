package com.vishal.collaborativefarming.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vishal.collaborativefarming.Client_HomePage;
import com.vishal.collaborativefarming.Client_Login;
import com.vishal.collaborativefarming.test_activity;

import java.util.HashMap;

public class User_Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public void  session(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String emailid,String password){
        //save session of user whenever user is logged in
        String email=emailid;
        String pass=password;


        editor.putString(SESSION_KEY,email).commit();
    }

    public int getSession(){
        //return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();
    }
}
