package com.example.schoolerp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.example.schoolerp.R;
import com.example.schoolerp.adapters.AttendanceAdapter;
import com.example.schoolerp.models.Attendance;
import com.google.firebase.firestore.*;

import java.util.*;

public class AttendanceListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Attendance> attendanceList;
    AttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        attendanceList = new ArrayList<>();
        adapter = new AttendanceAdapter(attendanceList);
        recyclerView.setAdapter(adapter);

        FirebaseFirestore.getInstance()
                .collection("attendance")
                .addSnapshotListener((value, error) -> {

                    attendanceList.clear();

                    if(value != null) {
                        for(DocumentSnapshot doc : value.getDocuments()) {
                            Attendance attendance = doc.toObject(Attendance.class);
                            attendanceList.add(attendance);
                        }
                    }

                    adapter.notifyDataSetChanged();
                });
    }
}