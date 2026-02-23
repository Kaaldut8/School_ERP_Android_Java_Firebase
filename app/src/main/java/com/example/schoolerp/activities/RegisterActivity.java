package com.example.schoolerp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolerp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    RadioGroup roleGroup;
    Button registerBtn;

    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        roleGroup = findViewById(R.id.roleGroup);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            int selectedRoleId = roleGroup.getCheckedRadioButtonId();
            if(selectedRoleId == -1){
                Toast.makeText(this,"Select Role",Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRole = findViewById(selectedRoleId);
            String role = selectedRole.getText().toString().toLowerCase();

            auth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {

                            String uid = auth.getCurrentUser().getUid();

                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("email", userEmail);
                            userMap.put("role", role);

                            db.collection("users")
                                    .document(uid)
                                    .set(userMap);

                            Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();

                        } else {
                            Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}