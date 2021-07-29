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
     * @param student Object storing details of a student
     * @return studentId : id of the student added to db
     */
    int addStudent(Student student);
    /**
     * Method to register for Courses for the semester
     * @param courseId : id of the course which student is registering in
     * @param student Object storing details of a student
     */
    void registerForCourse(int courseId, Student student);
    /**
     * Method to view Courses registered for a student
     * @param student Object storing details of a student
     */
    void viewCourses(Student student);
    /**
     * Method to view grades of the student
     * @param student Object storing details of a student
     */
    void viewGrades(Student student);
    /**
     * Method to fetch Student object storing details of the student
     * @param userId : id of student whose details need to be fetched
     * @return student Object storing details of a student
     */
    Student fetchStudent(int userId);
}