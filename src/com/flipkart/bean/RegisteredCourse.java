package com.flipkart.bean;

public class RegisteredCourse {

    private int studentId;
    private int courseId;
    private int marks;
    private int sem;

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSem() {
        return sem;
    }
    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }


}
