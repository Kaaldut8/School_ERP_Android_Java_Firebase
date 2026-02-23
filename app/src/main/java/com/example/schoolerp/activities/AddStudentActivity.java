package com.example.schoolerp.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolerp.R;
import com.example.schoolerp.models.Student;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStudentActivity extends AppCompatActivity {

    EditText name, className, rollNo;
    Button addBtn;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.name);
        className = findViewById(R.id.className);
        rollNo = findViewById(R.id.rollNo);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(v -> {

            String studentName = name.getText().toString().trim();
            String studentClass = className.getText().toString().trim();
            String studentRoll = rollNo.getText().toString().trim();

            if(studentName.isEmpty() || studentClass.isEmpty() || studentRoll.isEmpty()){
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show();
                return;
            }

            String id = db.collection("students").document().getId();

            Student student = new Student(id, studentName, studentClass, studentRoll);

            db.collection("students")
                    .document(id)
                    .set(student)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this,"Student Added",Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });
    }
}