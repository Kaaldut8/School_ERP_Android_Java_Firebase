package com.example.schoolerp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolerp.R;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    Button addStudentBtn, viewStudentsBtn, logoutBtn, viewAttendanceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addStudentBtn = findViewById(R.id.addStudentBtn);
        viewStudentsBtn = findViewById(R.id.viewStudentsBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        viewAttendanceBtn = findViewById(R.id.viewAttendanceBtn);

        String role = getIntent().getStringExtra("role");

        if(role != null && role.equals("student")){
            addStudentBtn.setVisibility(View.GONE);
        }

        addStudentBtn.setOnClickListener(v ->
                startActivity(new Intent(this, AddStudentActivity.class)));

        viewStudentsBtn.setOnClickListener(v ->
                startActivity(new Intent(this, StudentListActivity.class)));

        viewAttendanceBtn.setOnClickListener(v ->
                startActivity(new Intent(this, AttendanceListActivity.class)));

        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}