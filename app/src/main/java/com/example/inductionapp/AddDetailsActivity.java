package com.example.inductionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddDetailsActivity extends AppCompatActivity {

    private EditText activityName;
    private EditText nameOfFaculty;
    private EditText imageUrl;
    private EditText detailDescription;
    private EditText roomNo;
    private EditText blockDetails;
    private Button addButton;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Details").child("Activities");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        activityName = findViewById(R.id.useractivityname);
        nameOfFaculty = findViewById(R.id.nameoffaculty);
        imageUrl = findViewById(R.id.imageurl);
        detailDescription = findViewById(R.id.detaildescription);
        roomNo = findViewById(R.id.roomno);
        blockDetails = findViewById(R.id.blockdetails);
        addButton = findViewById(R.id.adddetailsbutton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detailsActivityName = activityName.getText().toString();
                String detailsFacultyName = nameOfFaculty.getText().toString();
                String detailsImageUrl = imageUrl.getText().toString();
                String detailsDescriptionString = detailDescription.getText().toString();
                String detailsRoomNo = roomNo.getText().toString();
                String detailsBlock = blockDetails.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("name", detailsActivityName);
                userMap.put("facultyName", detailsFacultyName);
                userMap.put("image", detailsImageUrl);
                userMap.put("description", detailsDescriptionString);
                userMap.put("room", detailsRoomNo);
                userMap.put("block", detailsBlock);

                root.child(detailsActivityName).setValue(userMap);

                startActivity((new Intent(AddDetailsActivity.this, AdminDetailsActivity.class)));
            }
        });
    }
}