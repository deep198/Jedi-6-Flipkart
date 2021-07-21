package com.flipkart.bean;

public class Course {

    private int courseId;
    private String courseName;
    private String department;
    private int professorId;

    //NEW ADDED
    private int credits;
    private int studentsEnrolled;
    private String grade;


    public int getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(int studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
