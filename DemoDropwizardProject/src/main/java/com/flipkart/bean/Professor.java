package com.flipkart.bean;

import java.util.List;

public class Professor extends User {

    private int professorId;
    private String name;
    public String department;
    public List<Course> signedupCourses;

    public int getProfessorId() {
        return professorId;
    }
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getsignedupCourses(){
        return signedupCourses;
    }

    public void setsignedupCourses(List<Course> signedupCourses){
        this.signedupCourses= signedupCourses;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

}
