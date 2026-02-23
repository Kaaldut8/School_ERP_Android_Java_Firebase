package com.example.schoolerp.adapters;

import android.content.Intent;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolerp.R;
import com.example.schoolerp.activities.UpdateStudentActivity;
import com.example.schoolerp.models.Attendance;
import com.example.schoolerp.models.Student;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Student student = studentList.get(position);

        holder.name.setText(student.getName());
        holder.className.setText("Class: " + student.getClassName());
        holder.rollNo.setText("Roll No: " + student.getRollNo());

        holder.itemView.setOnLongClickListener(v -> {
            FirebaseFirestore.getInstance()
                    .collection("students")
                    .document(student.getId())
                    .delete();
            return true;
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), UpdateStudentActivity.class);
            intent.putExtra("id", student.getId());
            intent.putExtra("name", student.getName());
            intent.putExtra("className", student.getClassName());
            intent.putExtra("rollNo", student.getRollNo());
            v.getContext().startActivity(intent);
        });

        holder.markAttendanceBtn.setOnClickListener(v -> {

            String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .format(new Date());

            String docId = student.getId() + "_" + today;

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Check today's attendance first
            db.collection("attendance")
                    .document(docId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {

                        if(documentSnapshot.exists()) {
                            String currentStatus = documentSnapshot.getString("status");

                            if("Present".equals(currentStatus)) {
                                holder.markAttendanceBtn.setText("Mark Absent");
                            } else {
                                holder.markAttendanceBtn.setText("Mark Present");
                            }
                        } else {
                            holder.markAttendanceBtn.setText("Mark Present");
                        }
                    });
            // Toggle attendance on click
            holder.markAttendanceBtn.setOnClickListener(b -> {

                db.collection("attendance")
                        .document(docId)
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {

                            String status;

                            if (documentSnapshot.exists()) {
                                String currentStatus = documentSnapshot.getString("status");
                                status = "Present".equals(currentStatus) ? "Absent" : "Present";
                            } else {
                                status = "Present";
                            }

                            final String finalStatus = status;

                            Attendance attendance = new Attendance(
                                    student.getId(),
                                    student.getName(),
                                    today,
                                    finalStatus
                            );

                            db.collection("attendance")
                                    .document(docId)
                                    .set(attendance)
                                    .addOnSuccessListener(unused -> {

                                        if ("Present".equals(finalStatus)) {
                                            holder.markAttendanceBtn.setText("Mark Absent");
                                        } else {
                                            holder.markAttendanceBtn.setText("Mark Present");
                                        }

                                    });
                        });
            });
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, className, rollNo;
        Button markAttendanceBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            className = itemView.findViewById(R.id.className);
            rollNo = itemView.findViewById(R.id.rollNo);
            markAttendanceBtn = itemView.findViewById(R.id.markAttendanceBtn);
        }
    }
}