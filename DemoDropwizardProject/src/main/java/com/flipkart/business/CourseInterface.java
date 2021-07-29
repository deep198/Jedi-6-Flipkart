package com.flipkart.business;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

/**
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public interface CourseInterface {
    /**
     * Method to view valid course details for Student
     * @param student Object storing student details
     */
    void viewCourses(Student student);
    /**
     * Method to view course details for Professor
     */
    void displayCoursesProfessor();
    /**
     * Method to add Course to DB
     * @param course Object storing course details
     */
    void addCourse(Course course);
    /**
     * Method to delete Course from DB
     * @param courseId Id of course to be deleted from courses
     * @throws  CourseNotFoundException
     */
    public void deleteCourse(int courseId)throws CourseNotFoundException;
    /**
     * Method to delete course for a particular Professor
     * @param courseId
     * @param professorId
     */
    void deleteprofcourse(int courseId, int professorId);
    /**
     * Method to select course for a particular Professor
     * @param courseID Id of course to be selected
     * @param professorId Id of professor to select course
     */
    void selectprofcourse(int courseID, int professorId);
    /**
     * Method to display course details taught by a particular Professor
     * @param prof Professor object for whom selected courses are to be displayed
     */
    void displaySelectedProfCourse(Professor prof);
}