package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentInterface {

    int addStudent(Student student);
    void registerForCourse(int courseId, Student student);

    void viewCourses(Student student);

    void viewGrades(Student student);

    Student fetchStudent(int userId);

}
