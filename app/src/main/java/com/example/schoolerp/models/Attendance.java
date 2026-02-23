package com.example.schoolerp.models;

public class Attendance {

    private String studentId;
    private String studentName;
    private String date;
    private String status;

    public Attendance() {}

    public Attendance(String studentId, String studentName, String date, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
    }

    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}