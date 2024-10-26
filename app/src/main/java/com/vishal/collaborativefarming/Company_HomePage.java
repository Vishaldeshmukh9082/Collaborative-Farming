package com.vishal.collaborativefarming;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vishal.collaborativefarming.databinding.ActivityCompanyHomePageBinding;

public class Company_HomePage extends AppCompatActivity {
    Button button;
    ActivityCompanyHomePageBinding binding;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_company_home_page);



//        button=findViewById(R.id.company_logoutbtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences pref=getSharedPreferences("companylogin",MODE_PRIVATE);
//                SharedPreferences.Editor editor=pref.edit();
//                editor.putString("cid",null);
//                editor.apply();
//                Intent intent = new Intent(Company_HomePage.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//
           // }
        //});
        binding = ActivityCompanyHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new CompanyHomeFragment());
        binding.bottomNavigationView1.setBackground(null);
        binding.bottomNavigationView1.setOnItemSelectedListener(item -> {
           switch (String.valueOf(item.getTitle())) {
               case "Home":
                   replaceFragment(new CompanyHomeFragment());
                   break;
               case "Properties":
                   replaceFragment(new CompanyFarmsFragment());
                   break;
               case "Profile":
                   replaceFragment(new CompanyProfileFragment());
                   break;

           }
           return true;
       });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}