package com.vishal.collaborativefarming;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
     private Context context;
     Connection conn;
    Activity activity;
    private ArrayList<String> surveyno,village,taluka,district,feildarea,description,image;

     CustomAdapter(Context context, ArrayList surveyno, ArrayList village, ArrayList taluka, ArrayList district, ArrayList feildarea, ArrayList description, ArrayList image) {
        this.context = context;
        this.surveyno = surveyno;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.feildarea = feildarea;
        this.description = description;
        this.image = image;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mycustomlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView1.setText("Survey NO :"+String.valueOf(surveyno.get(position)));
        holder.textView2.setText("Village :"+String.valueOf(village.get(position)));
        holder.textView3.setText("Feild Area :"+String.valueOf(feildarea.get(position))+" (acre)");
        holder.imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteFeild(String.valueOf(surveyno.get(position)));
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Client_HomePage.class);

                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return surveyno.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3;
        ImageView imageView9;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView9=itemView.findViewById(R.id.imageView9);
        }
    }

    void DeleteFeild(String surveyno) {
        DBHelper db = new DBHelper();
        conn = db.connectionclass();
        if (conn != null) {
            try {
                String query = "delete from feilds where surveyno='" + surveyno + "';";
                PreparedStatement statement = conn.prepareStatement(query);
                int rs = statement.executeUpdate();
                if (rs != 0) {
                }

            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
        } else {
            Log.d("Connection", "NotConnected");
        }
    }
}


