package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentInterface {
    void addCourse(int courseId, Student student);

    void viewCourses(Student student);

    void viewGrades(Student student);
}
