package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.Grade;

import java.util.List;
public interface StudentDaoInterface {

    public void addCourse(int courseId ,Student student) ;
    public void dropCourse(int courseId, Student student);
    public List<Course> viewCourses(Student student);
    public List<Grade> viewGrades(Student student);
    public void getUnapproved();
    public void approveStudent(List<Integer> students);

    Student fetchStudent(int userId);

    void displayStudents();
}