package com.flipkart.bean;

import java.util.List;

public class Student extends User {
    private int studentId;
    private String name;
    private String department;
    private int sem;
    private List<RegisteredCourse> registeredCourses;
    private boolean paymentStatus;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public List<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }



}
