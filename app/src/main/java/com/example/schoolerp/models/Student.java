package com.example.schoolerp.models;

public class Student {

    private String id;
    private String name;
    private String className;
    private String rollNo;

    // Empty constructor required for Firestore
    public Student() {
    }

    public Student(String id, String name, String className, String rollNo) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.rollNo = rollNo;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}