package com.example.inductionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminActivity extends AppCompatActivity {

    Date date=new Date();
    String dayWeekText = new SimpleDateFormat("EEEE").format(date);
    String monthText = new SimpleDateFormat("MMMM").format(date);
    String daynum = new SimpleDateFormat("dd").format(date);
    String yearnum = new SimpleDateFormat("yyyy").format(date);

    private TextView day;
    private TextView month;
    private TextView year;
    private TextView daydate;

    Button add, details, reload, logout;
    private RecyclerView recyclerView;
    private FirebaseDatabase fbase = FirebaseDatabase.getInstance();
    private DatabaseReference root = fbase.getReference().child("Activities").child(dayWeekText);
    private MyAdminAdapter adapter;
    private ArrayList<ActivitiesModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        day = findViewById(R.id.day);
        day.setText(dayWeekText);

        daydate = findViewById(R.id.daydate);
        daydate.setText(daynum);

        month = findViewById(R.id.month);
        month.setText(monthText);

        year = findViewById(R.id.year);
        year.setText(yearnum);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<ActivitiesModel>();
        adapter = new MyAdminAdapter(AdminActivity.this, list);

        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ActivitiesModel model = dataSnapshot.getValue(ActivitiesModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout = findViewById(R.id.logout);

        logout.setOnClickListener((v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(AdminActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminActivity.this, StartActivity.class));
        }));

        details = findViewById(R.id.admindetails);

        details.setOnClickListener((v -> {
            startActivity(new Intent(AdminActivity.this, AdminDetailsActivity.class));
        }));

        add = findViewById(R.id.add);

        add.setOnClickListener((v -> {
            startActivity(new Intent(AdminActivity.this, AddActivity.class));
        }));

        reload = findViewById(R.id.activityreload);

        reload.setOnClickListener((v -> {
            startActivity(getIntent());
        }));

        logout = findViewById(R.id.logout);
        
    }
}