package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.Grade;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.CloseConnection;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.SQLQueries.DISPLAY_UNAPPROVED;

public class StudentDaoOperation implements StudentDaoInterface,CloseConnection{
    // add a course by student
    public void addCourse(int courseId ,Student student) {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt = connection.prepareStatement(SQLQueries.ADD_COURSE);
            //check ADD_COURSE
            int studentId= student.getStudentId();
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setInt(3, -1);
            stmt.executeUpdate();
            System.out.println("Course with courseId="+courseId+" added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    // drop a course by student against a courseID
    public void dropCourse(int courseId, Student student) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt=connection.prepareStatement(SQLQueries.DROP_COURSE);
            int studentId= student.getStudentId();
            //check
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            int rs = stmt.executeUpdate();
            if(rs>0)
            {
                System.out.println("Course dropped !");
                return;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Course not found !");
    }

    @Override
    public List<Course> viewCourses(Student student) {
        return null;
    }

    @Override
    public List<Grade> viewGrades(Student student) {
        return null;
    }

    // display list of courses selected by student
    public List<Course> displayRegisteredCourses(Student student) {
        Connection connection= DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt= connection.prepareStatement(SQLQueries.VIEW_SELECTED_COURSES);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            List<Course> list= new ArrayList<Course>();
            while(rs.next())
            {
                Course course = new Course();
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                list.add(course);
            }
            return list;
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void getUnapproved(){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        try {
            //Declaring prepared statement and executing query
            Statement stmt = connection.createStatement();
            //Retrieving data
            ResultSet rs = stmt.executeQuery(DISPLAY_UNAPPROVED);
            System.out.println("Displaying List of Unapproved Students ");
            System.out.println();
            // iterate through the java resultset
            while (rs.next())
            {
                String name = rs.getString("Name");
                int studentId = rs.getInt("studentId");
                System.out.println("Name : "+name + " StudentId : " + studentId);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void approveStudent(List<Integer> students){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        for(Integer student : students ){
            try {
                //Declaring prepared statement and executing query
                stmt = connection.prepareStatement(SQLQueries.APPROVE_STUD);
                stmt.setInt(1, student);
                //Executing query
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}