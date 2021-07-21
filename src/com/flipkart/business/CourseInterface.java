package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

public interface CourseInterface {
    void viewCourses(Student student);

    void displayCoursesProfessor();
    
    void addCourse(Course course);

    public void deleteCourse(int courseId)throws CourseNotFoundException;}
