package com.flipkart.bean;

import java.util.List;

public class GradeCard {
    private int studentId;
    private List<RegisteredCourse> registeredCourses;

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public List<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }
    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

}

