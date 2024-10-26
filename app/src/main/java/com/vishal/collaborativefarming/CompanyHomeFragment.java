package com.vishal.collaborativefarming;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.collaborativefarming.database.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CompanyHomeFragment extends Fragment {

    TextView textView;
    ArrayAdapter<String> districtadapter, talukaadapter;
    String[] districtsel = {"satara", "kolhapur"};
    String[] satara = {"jawali", "khatav"};
    String[] kolhapur = {"panhala", "shirol"};
    Connection conn;
    Button button;


    RecyclerView recyclerView;
    ArrayList<String> surveyno, village, taluka, district, feildarea, description, image,landowner;
    LandslayoutAdapter landslayoutAdapter;
    String selecteddistrict, selectedtaluka;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_company_home_page, container, false);
        AutoCompleteTextView autoCompletedistrict = view.findViewById(R.id.district);
        AutoCompleteTextView autoCompletetaluka = view.findViewById(R.id.taluka);

        districtadapter = new ArrayAdapter<String>(getContext(), R.layout.list_item1, districtsel);
        autoCompletedistrict.setAdapter(districtadapter);
        talukaadapter = new ArrayAdapter<String>(getContext(), R.layout.list_item1, satara);
        autoCompletetaluka.setAdapter(talukaadapter);
        textView = view.findViewById(R.id.textView2);

        autoCompletedistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selecteddistrict = parent.getItemAtPosition(position).toString();

                if (selecteddistrict == "satara") {
                    talukaadapter = new ArrayAdapter<String>(getContext(), R.layout.list_item1, satara);
                    autoCompletetaluka.setAdapter(talukaadapter);

                }
                if (selecteddistrict == "kolhapur") {
                    talukaadapter = new ArrayAdapter<String>(getContext(), R.layout.list_item1, kolhapur);
                    autoCompletetaluka.setAdapter(talukaadapter);
                }

                //Toast.makeText(getContext(), "Selected: " + selecteddistrict, Toast.LENGTH_SHORT).show();
            }

        });
        autoCompletetaluka.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedtaluka = parent.getItemAtPosition(position).toString();

            }
        });
        button = view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.setText(selecteddistrict);
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);

                surveyno = new ArrayList<>();
                village = new ArrayList<>();
                taluka = new ArrayList<>();
                district = new ArrayList<>();
                feildarea = new ArrayList<>();
                description = new ArrayList<>();
                landowner =new ArrayList<>();
                image = new ArrayList<>();

                //getFeilds();
                SharedPreferences shref = requireContext().getSharedPreferences("companylogin", MODE_PRIVATE);
                String id = shref.getString("cid", null);
                DBHelper db = new DBHelper();
                conn = db.connectionclass();
                if (conn != null) {
                    try {
                        String query = "SELECT * FROM feilds where district='" + selecteddistrict + "'and taluka='" + selectedtaluka + "';";
                        PreparedStatement statement = conn.prepareStatement(query);
                        ResultSet rs = statement.executeQuery();
                        while (rs.next()) {
                            surveyno.add(rs.getString(2));
                            village.add(rs.getString(3));
                            taluka.add(rs.getString(4));
                            district.add(rs.getString(5));
                            feildarea.add(rs.getString(6));
                            description.add(rs.getString(7));
                            image.add(rs.getString(8));
                            landowner.add(rs.getString(9));

                        }

                    } catch (Exception ex) {
                        Log.e("Error", ex.toString());
                    }
                } else {
                    Log.d("Connection", "NotConnected");
                }
                landslayoutAdapter = new LandslayoutAdapter(getActivity(),getContext(), surveyno, village, taluka, district, feildarea, description, image,landowner);
                recyclerView.setAdapter(landslayoutAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        });
        return view;
    }
}
