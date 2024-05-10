package com.vishal.collaborativefarming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
     private Context context;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.textView1.setText(String.valueOf(surveyno.get(position)));
        holder.textView2.setText(String.valueOf(village.get(position)));
        holder.textView3.setText(String.valueOf(feildarea.get(position)));
        Log.e("Error",surveyno.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return surveyno.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }
}
