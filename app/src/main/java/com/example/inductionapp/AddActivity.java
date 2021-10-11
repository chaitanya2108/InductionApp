package com.example.inductionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    Date date=new Date();
    String dayWeekText = new SimpleDateFormat("EEEE").format(date);

    private EditText nameOfActivity;
    private Button addButton;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Activities").child(dayWeekText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameOfActivity = findViewById(R.id.useractivityname);
        addButton = findViewById(R.id.adddetailsbutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameActivity = nameOfActivity.getText().toString();
                String message = nameActivity + "is going on now!!";

                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("activityName", nameActivity);

                root.child(nameActivity).setValue(userMap);

                startActivity(new Intent(AddActivity.this, AdminActivity.class));
            }
        });

    }
}