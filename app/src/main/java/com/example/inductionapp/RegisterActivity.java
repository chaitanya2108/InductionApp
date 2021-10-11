package com.example.inductionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText usn;
    private EditText branch;
    private EditText email;
    private EditText password;
    private Button register;
    private String regularExpression
            ="^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[@$!])[A-Za-z\\d@$!]{8,}$";

    private FirebaseAuth auth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.username);
        usn = findViewById(R.id.userusn);
        branch = findViewById(R.id.userbranch);
        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);
        register = findViewById(R.id.register_button);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String txt_name = name.getText().toString();
                String txt_usn = usn.getText().toString();
                String txt_branch = branch.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (TextUtils.isEmpty(txt_name) || TextUtils.isEmpty(txt_usn) || TextUtils.isEmpty(txt_branch) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (validatePassword(txt_password)) {
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txt_email, txt_password, txt_name, txt_usn, txt_branch);
                }
            }
        });

    }

    private void registerUser(String email, String password, String name, String usn, String branch) {

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = auth.getCurrentUser();
                Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                DocumentReference df = fStore.collection("Users").document(user.getUid());
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("Name", name);
                userInfo.put("Email", email);
                userInfo.put("USN", usn);
                userInfo.put("Branch", branch);

                userInfo.put("isUser", "1");

                df.set(userInfo);

                startActivity(new Intent(RegisterActivity.this, StartActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean validatePassword(String password) {
        Pattern pattern= Pattern.compile(regularExpression);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }
}