package com.flipkart.business;

import com.flipkart.DAO.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

import static com.flipkart.constant.UserRole.STUDENT;

public class StudentOperation implements StudentInterface{
    StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
    UserDaoInterface userDaoOperation = new UserDaoOperation();

    public static void dropCourse(int courseId, Student student) {
        RegisteredCourseDaoInterface registeredCourseOperation = new RegisteredCourseDaoOperation();
        registeredCourseOperation.dropCourseForStudent(student.getStudentId(), courseId, student.getSem());
        CourseDaoInterface courseDaoOperation = new CourseDaoOperation();
        courseDaoOperation.decrementEnrolledStudents(courseId);
    }

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

    @Override
    public void registerForCourse(int courseId, Student student) {
        RegisteredCourseDaoInterface registeredCourseOperation = new RegisteredCourseDaoOperation();
        registeredCourseOperation.registerCourseForStudent(student.getStudentId(), courseId, student.getSem());
        CourseDaoInterface courseDaoOperation = new CourseDaoOperation();
        courseDaoOperation.incrementEnrolledStudents(courseId);
    }

    @Override
    public void viewCourses(Student student) {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        List<Course> courses =  studentDaoOperation.viewRegisteredCourses(student);
        System.out.println("LIST OF COURSES REGISTERED BY " + student.getName());
        for (Course course : courses) {
            System.out.println("Course Id : " + course.getCourseId());
        }


    }

    @Override
    public void viewGrades(Student student) {

    }

    @Override
    public Student fetchStudent(int userID){
        StudentDaoInterface studentDao= new StudentDaoOperation();
        Student student=studentDao.fetchStudent(userID);
        return student;
    }
}
