package com.vishal.collaborativefarming.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.vishal.collaborativefarming.MainActivity;
import com.vishal.collaborativefarming.model.User;

public class DbHandler extends SQLiteOpenHelper {
    private static final String DB_NAME="collabfarming";
    private static final int DB_version=1;

    private static final String User_Table="user";
    private static final String User_id="uid";
    private static final String User_name="name";
    private static final String User_contactno="contactno";
    private static final String User_email="email";
    private static final String User_gender="gender";
    private static final String User_address="address";
    private static final String User_password="password";


    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+User_Table+"("+User_id+"INTEGER PRIMARY KEY,"+User_name+" TEXT NOT NULL, " +User_contactno+ " TEXT NOT NULL, "+User_email+" TEXT NOT NULL, "+User_gender+" TEXT NOT NULL, "+User_address+" TEXT NOT NULL, "+User_password+" TEXT NOT NULL);";
        //Toast.makeText(MainActivity.this, "Companys Clicked", Toast.LENGTH_SHORT).show();
        Log.d("usertable", "User Tabel Created "+query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + User_Table);
//        onCreate(db);
    }

    public void userRegister(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(User_name, user.getName());
        cv.put(User_contactno, user.getContactno());
        cv.put(User_email, user.getEmail());
        cv.put(User_gender, user.getGender());
        cv.put(User_address, user.getAddress());
        cv.put(User_password, user.getPassword());

        db.insert(User_Table, null, cv);
        Log.d("usertable", "Successfully inserted");
        db.close();
    }
}
