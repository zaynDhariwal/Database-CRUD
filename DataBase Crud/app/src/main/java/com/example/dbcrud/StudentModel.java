package com.example.dbcrud;

public class StudentModel {
    public String Name;
    public String rollNo;
    public String isEnrolled;
    public int Id;

    public StudentModel(int id,String name, String rollNo, String isEnrolled) {
        Id = id;
        Name = name;
        this.rollNo = rollNo;
        this.isEnrolled = isEnrolled;
    }

    public StudentModel(String name, String rollNo, String isEnrolled) {
        Name = name;
        this.rollNo = rollNo;
        this.isEnrolled = isEnrolled;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoll() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(String enrolled) {
        isEnrolled = enrolled;
    }
}

