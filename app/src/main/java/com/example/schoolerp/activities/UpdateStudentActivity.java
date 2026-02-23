package com.example.schoolerp.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolerp.R;
import com.example.schoolerp.models.Student;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText name, className, rollNo;
    Button updateBtn;
    FirebaseFirestore db;
    String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.name);
        className = findViewById(R.id.className);
        rollNo = findViewById(R.id.rollNo);
        updateBtn = findViewById(R.id.updateBtn);

        studentId = getIntent().getStringExtra("id");

        name.setText(getIntent().getStringExtra("name"));
        className.setText(getIntent().getStringExtra("className"));
        rollNo.setText(getIntent().getStringExtra("rollNo"));

        updateBtn.setOnClickListener(v -> {

            Student student = new Student(
                    studentId,
                    name.getText().toString(),
                    className.getText().toString(),
                    rollNo.getText().toString()
            );

            db.collection("students")
                    .document(studentId)
                    .set(student);

            Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}