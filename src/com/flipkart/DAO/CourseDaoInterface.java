package com.flipkart.DAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import java.util.List;
/**
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public interface CourseDaoInterface {
    /**
     * Method to Display courses for student
     * @param student object which contains the details of the student
     * @return get the courses offered to students
     */
    public List<Course> displayCourses(Student student);
    /**
     * Professor can view the selected course
     * @param professorid Id of professor who selected course
     * @return get the list of courses for professor
     */
    List<Course> selectedprofcourse(int professorid);
    /**
     * Admin can add the course in the DB
     * @param course object which gives the details about the course
     */
    public void insertCourse(Course course) ;
    /**
     * Admin can delete the course from the DB
     * @param courseId Id which course to be dropped
     */
    public void deleteCourse(int courseId) throws CourseNotFoundException;
    /**
     * Display list of courses available for professor
     * @return List of available courses for the professor
     */
    public List<Course> displayCoursesProfessor();
    /**
     * Professor can deselect a course
     * @param courseId Id of which course to be dropped
     * @param ProfessorID Id of professor who drops course
     */
    public void deleteProfessorCourse(int courseId, int ProfessorID);
    /**
     * Professor can select a course
     * @param courseId :which course to be dropped
     * @param professorId Id of professor who selected course
     */
    public void selectCourse(int courseId, int professorId);
    /**
     * SQL command to increment number of enrolled students in a course
     * @param courseId Id of course for which enrolled students should be incremented
     */
    void incrementEnrolledStudents(int courseId);
    /**
     * SQL command to decrement number of enrolled students in a course
     * @param courseId Id of course for which enrolled students should be decremented
     */
    void decrementEnrolledStudents(int courseId);
    /**
     * Eligible course for the students of given semester and branch
     * @param student  details of the student
     */
    public void getEligibleCoursesToSelectForStudent(Student student);
}
