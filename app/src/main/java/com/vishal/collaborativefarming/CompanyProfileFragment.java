package com.vishal.collaborativefarming;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class CompanyProfileFragment extends Fragment {

    TextView textView;
    Button butt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_company_profile, container, false);
        butt=view.findViewById(R.id.logout_btn);
        textView=view.findViewById(R.id.conumber);

        SharedPreferences shref=getContext().getSharedPreferences("companylogin",MODE_PRIVATE);
        String check=shref.getString("cid",null);
        textView.setText(check.toString());

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getContext().getSharedPreferences("companylogin",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("cid",null);
                editor.apply();
                Intent intent=new Intent(getContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
}