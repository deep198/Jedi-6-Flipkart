package com.flipkart.business;

import com.flipkart.DAO.CourseDaoInterface;
import com.flipkart.DAO.CourseDaoOperation;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

/**
 *
 * @author JEDI-Group05
 * Implementation for Admin Operations
 *
 */
public class CourseOperation implements CourseInterface {
    /**
     * Method to view valid course details for Student
     * @param student Object storing student details
     */
    public void viewCourses(Student student) {
        System.out.println("****************************************LIST OF AVAILABLE COURSES*****************************************1****");
        System.out.println("COURSE ID      COURSE NAME                       CREDITS         ");
        CourseDaoInterface newCourse2=new CourseDaoOperation();
        List<Course> courses = newCourse2.displayCourses(student);
        for(Course course:courses) {
            if(course.getStudentsEnrolled()<10) {
                System.out.println(course.getCourseId()+"               "+course.getCourseName()+"               "+"                  "+course.getCredits());
            }
        }
        System.out.println("*************************************************************************************************************");
    }
    /**
     * Method to view course details for Professor
     */
    public void displayCoursesProfessor() {
        System.out.println("****************************LIST OF AVAILABLE COURSES************************************");
        System.out.println("COURSE ID      COURSE TITLE        ");
        CourseDaoInterface newCourse1=new CourseDaoOperation();
        List<Course> courses = newCourse1.displayCoursesProfessor();
        courses.forEach(course -> System.out.println(course.getCourseId()+"               "+course.getCourseName()+"               "));
        System.out.println("******************************************************************************************");
    }
    /**
     * Method to add Course to DB
     * @param course Object storing course details
     */
    public void addCourse(Course course) {
        CourseDaoInterface addCourse=new CourseDaoOperation();
        addCourse.insertCourse(course);
    }
    /**
     * Method to delete Course from DB
     * @param courseId ID of course to be deleted
     * @exception CourseNotFoundException
     */
    @Override
    public void deleteCourse(int courseId) throws CourseNotFoundException {
        CourseDaoInterface newCourse=new CourseDaoOperation();
        newCourse.deleteCourse(courseId);
    }
    /**
     * Method to delete course for a particular Professor
     * @param courseId ID of course to deselect
     * @param professorId ID of professor to deselect course
     */
    public void deleteprofcourse(int courseId,int professorId)
    {
        CourseDaoInterface newprof=new CourseDaoOperation();
        newprof.deleteProfessorCourse(courseId, professorId);
    }
    /**
     * Method to select course for a particular Professor
     * @param courseID Id of course to be selected
     * @param professorId Id of professor to select course
     */
    @Override
    public void selectprofcourse(int courseID, int professorId)
    {
        CourseDaoInterface newprof1=new CourseDaoOperation();
        newprof1.selectCourse(courseID, professorId);
    }
    /**
     * Method to display course details taught by a particular Professor
     * @param prof Professor object for whom selected courses are to be displayed
     */
    @Override
    public void displaySelectedProfCourse(Professor prof)
    {
        System.out.println("****************************LIST OF SELECTED COURSES BY"+prof.getName()+" ************************************");
        System.out.println("COURSE ID      COURSE TITLE        ");
        CourseDaoInterface newCourse1=new CourseDaoOperation();
        List<Course> courses = newCourse1.selectedprofcourse(prof.getProfessorId());
        courses.forEach(course -> System.out.println(course.getCourseId()+"               "+course.getCourseName()+"               "));
        System.out.println("******************************************************************************************");
    }
}