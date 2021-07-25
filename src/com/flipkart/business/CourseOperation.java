package com.flipkart.business;
import com.flipkart.DAO.CourseDaoInterface;
import com.flipkart.DAO.CourseDaoOperation;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import java.util.List;
//import com.flipkart.DAO.AdminDaoInterface;
//import com.flipkart.DAO.AdminDaoOperation;
public class CourseOperation implements CourseInterface {
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
    public void displayCoursesProfessor() {
        System.out.println("****************************LIST OF AVAILABLE COURSES************************************");
        System.out.println("COURSE ID      COURSE TITLE        ");
        CourseDaoInterface newCourse1=new CourseDaoOperation();
        List<Course> courses = newCourse1.displayCoursesProfessor();
        courses.forEach(course -> System.out.println(course.getCourseId()+"               "+course.getCourseName()+"               "));
        System.out.println("******************************************************************************************");
    }
    public void addCourse(Course course) {
        CourseDaoInterface addCourse=new CourseDaoOperation();
        addCourse.insertCourse(course);
    }
    @Override
    public void deleteCourse(int courseId) throws CourseNotFoundException {
        // TODO Auto-generated method stub
        CourseDaoInterface newCourse=new CourseDaoOperation();
        newCourse.deleteCourse(courseId);
    }
    public void deleteprofcourse(int courseId,int professorId)
    {
        CourseDaoInterface newprof=new CourseDaoOperation();
        newprof.deleteProfessorCourse(courseId, professorId);
    }
    @Override
    public void selectprofcourse(int courseID, int professorId)
    {
        CourseDaoInterface newprof1=new CourseDaoOperation();
        newprof1.selectCourse(courseID, professorId);
    }
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