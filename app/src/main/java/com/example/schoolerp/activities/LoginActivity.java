package com.example.schoolerp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.example.schoolerp.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, registerBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(v -> {
            auth.signInWithEmailAndPassword(
                    email.getText().toString().trim(),
                    password.getText().toString().trim()
            ).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    String uid = auth.getCurrentUser().getUid();

                    FirebaseFirestore.getInstance()
                            .collection("users")
                            .document(uid)
                            .get()
                            .addOnSuccessListener(documentSnapshot -> {

                                String role = documentSnapshot.getString("role");

                                Intent intent = new Intent(this, DashboardActivity.class);
                                intent.putExtra("role", role);
                                startActivity(intent);
                                finish();
                            });
                } else {
                    Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            });
        });

        registerBtn.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}