package com.example.schoolerp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolerp.R;
import com.example.schoolerp.adapters.StudentAdapter;
import com.example.schoolerp.models.Student;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter adapter;
    List<Student> studentList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        adapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("students")
                .addSnapshotListener((value, error) -> {

                    if(value != null) {
                        studentList.clear();

                        for(DocumentSnapshot doc : value.getDocuments()) {
                            Student student = doc.toObject(Student.class);
                            studentList.add(student);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
    }
}