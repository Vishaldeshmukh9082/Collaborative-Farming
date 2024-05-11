package com.vishal.collaborativefarming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.CompanieHolder>{

    Context context;
    String[] companyname;
    int[] copmanyimage;
    String[] location;
    String[] companydescription;

    public CompaniesAdapter(Context context, String[] companyname, int[] copmanyimage, String[] location, String[] companydescription) {
        this.context = context;
        this.companyname = companyname;
        this.copmanyimage = copmanyimage;
        this.location = location;
        this.companydescription = companydescription;
    }
    @NonNull
    @Override
    public CompanieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.company_layout, parent, false);
        return new CompaniesAdapter.CompanieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CompanieHolder holder, int position) {
        holder.companyname2.setText(companyname[position]);
        holder.location.setText(location[position]);
        holder.description.setText(companydescription[position]);
        holder.companyimage.setImageResource(copmanyimage[position]);
    }

    @Override
    public int getItemCount() {
        return companyname.length;
    }

    public class CompanieHolder extends RecyclerView.ViewHolder {
        TextView companyname2,location,description;
        ImageView companyimage;
        public CompanieHolder(@NonNull View itemView) {
            super(itemView);
            companyname2=itemView.findViewById(R.id.companyname2);
            location=itemView.findViewById(R.id.location2);
            description=itemView.findViewById(R.id.comapydescription2);
            companyimage=itemView.findViewById(R.id.companyimage2);
        }
    }
}
