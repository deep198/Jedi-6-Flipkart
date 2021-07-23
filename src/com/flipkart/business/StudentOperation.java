package com.flipkart.business;

import com.flipkart.bean.Student;

public class StudentOperation implements StudentInterface{
    public static void dropCourse(int courseId, Student student) {
    }

    @Override
    public int addStudent(Student student) {
        return 0;
    }

    @Override
    public void addCourse(int courseId, Student student) {



    }

    @Override
    public void viewCourses(Student student) {

    }

    @Override
    public void viewGrades(Student student) {

    }

    @Override
    public Student fetchStudent(String userName) {
        return null;
    }
}
