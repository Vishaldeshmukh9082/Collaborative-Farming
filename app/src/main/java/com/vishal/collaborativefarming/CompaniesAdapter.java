package com.vishal.collaborativefarming;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.CompanieHolder>{

    Context context;
    String[] companyname;
    int[] copmanyimage;
    String[] location;
    String[] companydescription;
    String[] companyemail;
    Activity activity;
    Connection conn;

    public CompaniesAdapter(Activity activity,Context context, String[] companyname, int[] copmanyimage, String[] location, String[] companydescription,String[] companyemail) {
        this.activity = activity;
        this.context = context;
        this.companyname = companyname;
        this.copmanyimage = copmanyimage;
        this.location = location;
        this.companydescription = companydescription;
        this.companyemail=companyemail;
    }
    @NonNull
    @Override
    public CompanieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.company_layout, parent, false);
        return new CompaniesAdapter.CompanieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanieHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.companyname2.setText(companyname[position]);
        holder.location.setText(location[position]);
        holder.description.setText(companydescription[position]);
        holder.companyimage.setImageResource(copmanyimage[position]);
        holder.applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context,Select_FeildPage.class);
//                intent.putExtra("companyemail",companyemail[position]);
//                Log.d("intent",intent.toString());
//                activity.startActivity(intent);
                SharedPreferences pref=context.getSharedPreferences("ulogin",MODE_PRIVATE);
                String id=pref.getString("uid", null);

                DBHelper db=new DBHelper();
                conn=db.connectionclass();
                try {
                    if (conn != null) {
                        String query="select * from feilds where userid='"+id+"';";
                        PreparedStatement preparedStatement=conn.prepareStatement(query);
                        ResultSet rs= preparedStatement.executeQuery();
                        while (rs.next()){
                            String surveyno=rs.getString(2);
                            String userid=rs.getString(9);
                            String query2="insert into rentfeilds (companyemail,feildsurveyno,userid)values('"+companyemail[position]+"','"+surveyno+"','"+userid+"');";
                            PreparedStatement statement=conn.prepareStatement(query2);
                            int rs2= statement.executeUpdate();
                            if(rs2>0){
                                Toast.makeText(context, "Feilds Apply Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Already Submitted", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }catch(Exception ex){
                    Log.e("Error",ex.toString());
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return companyname.length;
    }

    public class CompanieHolder extends RecyclerView.ViewHolder {
        TextView companyname2,location,description;
        ImageView companyimage;
        Button applybtn;
        public CompanieHolder(@NonNull View itemView) {
            super(itemView);
            companyname2=itemView.findViewById(R.id.companyname2);
            location=itemView.findViewById(R.id.location2);
            description=itemView.findViewById(R.id.comapydescription2);
            companyimage=itemView.findViewById(R.id.companyimage2);
            applybtn= itemView.findViewById(R.id.companyapplybtn);
        }
    }
}
