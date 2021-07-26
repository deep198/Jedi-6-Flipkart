package com.flipkart.business;

import com.flipkart.bean.Student;

/**
 *
 * @author JEDI-Group05
 * Interface for Student Operations
 *
 */

public interface StudentInterface {

    /**
     * Method to add Student to DB
     * @param Student Object storing details of a student
     * @return studentId
     */
    int addStudent(Student student);


    void registerForCourse(int courseId, Student student);

    void viewCourses(Student student);

    void viewGrades(Student student);

    Student fetchStudent(int userId);

}
