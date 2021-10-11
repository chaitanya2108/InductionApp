package com.example.inductionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminDetailsActivity extends AppCompatActivity {

    private Button add, reload;
    private RecyclerView recyclerView;
    private FirebaseDatabase fbase = FirebaseDatabase.getInstance();
    private DatabaseReference root = fbase.getReference().child("Details").child("Activities");
    private AdminDetailsAdapter adapter;
    private ArrayList<DetailsModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_details);

        recyclerView = findViewById(R.id.admindetailrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<DetailsModel>();
        adapter = new AdminDetailsAdapter(AdminDetailsActivity.this, list);

        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DetailsModel model = dataSnapshot.getValue(DetailsModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add = findViewById(R.id.adddetails);

        add.setOnClickListener((v -> {
            startActivity(new Intent(AdminDetailsActivity.this, AddDetailsActivity.class));
        }));

        reload = findViewById(R.id.detailsreload);

        reload.setOnClickListener((v -> {
            startActivity(getIntent());
        }));
    }
}