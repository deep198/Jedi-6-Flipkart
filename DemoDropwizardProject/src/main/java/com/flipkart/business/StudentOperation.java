package com.flipkart.business;

import com.flipkart.DAO.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

import static com.flipkart.constant.UserRole.STUDENT;

/**
 *
 * @author JEDI-Group05
 * Implementations of Student Operations
 *
 */
public class StudentOperation implements StudentInterface{
    StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
    UserDaoInterface userDaoOperation = new UserDaoOperation();

    /**
     * Method to drop courses
     * @param courseId
     * @param student Object storing details of a student
     */
    public static void dropCourse(int courseId, Student student) {
        RegisteredCourseDaoInterface registeredCourseOperation = new RegisteredCourseDaoOperation();
        registeredCourseOperation.dropCourseForStudent(student.getStudentId(), courseId, student.getSem());
        CourseDaoInterface courseDaoOperation = new CourseDaoOperation();
        courseDaoOperation.decrementEnrolledStudents(courseId);
    }
    /**
     * Method to add Student to DB
     * @param student Object storing details of a student
     * @return studentId : id of the student added to db
     */
    @Override
    public int addStudent(Student student) {
        int studentId = studentDaoOperation.createStudent(student);
        User user = new User();
        user.setUserId(studentId);
        user.setUserRole(STUDENT);
        user.setPassword(student.getPassword());
        user.setUserName(student.getUserName());
        userDaoOperation.createUser(user);
        return studentId;
    }
    /**
     * Method to register for Courses for the semester
     * @param courseId : id of the course which student is registering in
     * @param student Object storing details of a student
     */
    @Override
    public void registerForCourse(int courseId, Student student) {
        RegisteredCourseDaoInterface registeredCourseOperation = new RegisteredCourseDaoOperation();
        registeredCourseOperation.registerCourseForStudent(student.getStudentId(), courseId, student.getSem());
        CourseDaoInterface courseDaoOperation = new CourseDaoOperation();
        courseDaoOperation.incrementEnrolledStudents(courseId);
    }
    /**
     * Method to view Courses registered for a student
     * @param student Object storing details of a student
     */
    @Override
    public void viewCourses(Student student) {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        List<Course> courses = studentDaoOperation.viewRegisteredCourses(student);
        System.out.println("LIST OF COURSES REGISTERED BY " + student.getName());
        for (Course course : courses) {
            System.out.println("Course Id : " + course.getCourseId());
        }
    }
    /**
     * Method to view grades of the student
     * @param student Object storing details of a student
     */
    @Override
    public void viewGrades(Student student) {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        List<RegisteredCourse> registeredCourses = studentDaoOperation.viewGrades(student);
        System.out.println("SHOWING GRADES FOR " + student.getName());
        for (RegisteredCourse course : registeredCourses) {
            System.out.println("CourseId : " + course.getCourseId() + " Marks : " + course.getMarks() + " Grade : " + course.getGrade());
        }
    }
    /**
     * Method to fetch Student object storing details of the student
     * @param userID : id of student whose details need to be fetched
     * @return student Object storing details of a student
     */
    @Override
    public Student fetchStudent(int userID){
        StudentDaoInterface studentDao= new StudentDaoOperation();
        Student student=studentDao.fetchStudent(userID);
        return student;
    }
}