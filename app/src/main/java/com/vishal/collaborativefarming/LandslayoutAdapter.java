package com.vishal.collaborativefarming;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.util.ArrayList;

public class LandslayoutAdapter extends RecyclerView.Adapter<LandslayoutAdapter.MyViewHolder> {
    private Context context;
    Connection conn;
    Activity activity;
    ArrayList<String> surveyno,village,taluka,district,feildarea,description,image,landowner;
    private int position;


    LandslayoutAdapter(Activity activity,Context context, ArrayList surveyno, ArrayList village, ArrayList taluka, ArrayList district, ArrayList feildarea, ArrayList description, ArrayList image,ArrayList landowner) {
        this.context = context;
        this.activity = activity;
        this.surveyno = surveyno;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.feildarea = feildarea;
        this.description = description;
        this.image = image;
        this.landowner=landowner;
    }


    @NonNull
    @Override
    public LandslayoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.landslayout, parent, false);
        return new LandslayoutAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
         this.position=position;
        holder.textView1.setText("Survey NO :"+String.valueOf(surveyno.get(position)));
        holder.textView2.setText("Village :"+String.valueOf(village.get(position)));
        holder.textView3.setText("Feild Area :"+String.valueOf(feildarea.get(position))+" (acre)");

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionViewPage.class);
                intent.putExtra("surveyno", String.valueOf(surveyno.get(position)));
                intent.putExtra("feildarea", String.valueOf(feildarea.get(position)));
                intent.putExtra("village", String.valueOf(village.get(position)));
                intent.putExtra("taluka", String.valueOf(taluka.get(position)));
                intent.putExtra("district", String.valueOf(district.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                intent.putExtra("image", String.valueOf(image.get(position)));
                intent.putExtra("landowner", String.valueOf(landowner.get(position)));
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

        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.landsurveyno);
            textView2 = itemView.findViewById(R.id.landdescription);
            textView3 = itemView.findViewById(R.id.landarea);
            mainLayout=itemView.findViewById(R.id.landslayout);
        }
    }

}



