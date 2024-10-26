package com.vishal.collaborativefarming.database;

import android.os.StrictMode;
import android.util.*;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    Connection conn;
    String ip,dbname,password,uname,port;

    @SuppressWarnings("newAPI")
    public Connection connectionclass(){

        ip="192.168.0.102";
        dbname="collabfarmingdb";
        password="root";
        uname="root";
        port="3306";


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        try{

            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+dbname,uname,password);
            if(connection!=null){
                Log.d("Connection","connected");
            }

        }catch(Exception ex){
            Log.e("Error",ex.getMessage());
        }


        return connection;
    }

}
