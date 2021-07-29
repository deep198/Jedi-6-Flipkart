package com.flipkart.DAO;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import java.util.List;
/*
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public interface RegisteredCourseDaoInterface {
    /**
     * Method to view grade card of the student
     *
     * @param student Object:Get the details of the student
     * @return Studen's grade card which contains course,grade and marks
     */
    public List<RegisteredCourse> displayGradeCard(Student student);

    /**
     * Method to view upload card of the student
     *
     * @param studentId: To upload the grade of that particular student
     * @param courseId:  To upload the marks in the particular course
     * @param grade:     Grade in that particular course
     * @param marks:     Marks in that particular course
     */
    void uploadGrades(int studentId, int courseId, String grade, int marks);

    /**
     * Method to get the list of courses registered by the student
     * Number of registered courses for a student
     *
     * @param studentId : ID of student for whom course is to be registered
     * @param courseId : Course
     * @param sem:Semester
     */
    void registerCourseForStudent(int studentId, int courseId, int sem);

    /**
     * Method to drop the courses registered by the student
     *
     * @param studentId
     * @param courseId
     * @param sem:Semester
     */
    void dropCourseForStudent(int studentId, int courseId, int sem);
}