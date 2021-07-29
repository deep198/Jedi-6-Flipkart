package com.flipkart.bean;

import java.util.List;

public class Admin extends User{

    private int adminId;
    private String name;
    private List<Course> courses;

    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCourses(List courses) {
        this.courses = courses;
    }
    public List<Course> getCourses() {
        return courses;
    }

}