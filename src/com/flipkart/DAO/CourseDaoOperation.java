package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoOperation implements CourseDaoInterface {

    //List of Courses available to select for a student of particular branch and semester
    public List<Course> displayCourses(Student student) {

        //Establishing the connection
        Connection connection= DBConnectionHelper.getConnection();

        //Declaring prepared statement and executing query
        PreparedStatement stmt= null;
        try {
            int semester= student.getSem();
            String department= student.getDepartment();
            stmt= connection.prepareStatement(SQLQueries.DISPLAY_COURSES);
            stmt.setInt(1, semester);
            stmt.setString(2,department);

            ResultSet rs = stmt.executeQuery();

            List<Course> list= new ArrayList<Course>();

            //Creating ArrayList of Course
            while(rs.next())
            {
                Course course = new Course();
                course.setDepartment(department);
                course.setSem(semester);
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                course.setProfessorId(rs.getInt("professorId"));
                list.add(course);
            }
            return list;
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // Insert a new course in the database
    public void insertCourse(Course course)  {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        //Establishing the connection
        PreparedStatement stmt = null;

        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_COURSE);
            int courseID= course.getCourseId();
            String title= course.getCourseName();
            int semester= course.getSem();
            String branch=course.getDepartment();
//            int credits=course.getCredits();
            int profid=course.getProfessorId();

            stmt.setString(1, title);
            stmt.setInt(2, courseID);
//            stmt.setInt(3, credits);
            stmt.setInt(4, profid);
            stmt.setInt(5, semester);
            stmt.setString(6,branch);


            //Executing query
            stmt.executeUpdate();
            System.out.println("Course added!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    // Remove a course from database
    public void deleteCourse(int courseId) throws CourseNotFoundException {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        //Establishing the connection
        PreparedStatement stmt = null;
        try {

            stmt=connection.prepareStatement(SQLQueries.DELETE_COURSE+courseId);
            //Executing query
            int rs = stmt.executeUpdate();
            if(rs>0)
            {
                System.out.println("Course with courseId "+courseId+" deleted !");
                return;

            }
            throw new CourseNotFoundException();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }




    }

    // provide list of courses available for professor to select to teach
    public List<Course> displayCoursesProfessor() {

        Connection connection= DBConnectionHelper.getConnection();

        //Declaring prepared statement and executing query
        PreparedStatement stmt= null;

        try {
            stmt= connection.prepareStatement(SQLQueries.DISPLAY_COURSES_PROFESSOR);
            ResultSet rs = stmt.executeQuery();

            List<Course> list= new ArrayList<Course>();

            //Creating ArrayList of courses
            while(rs.next())
            {
                Course course = new Course();
                course.setDepartment(rs.getString("Branch"));
                course.setSem(rs.getInt("Semester"));
                course.setCourseId(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseTitle"));
                list.add(course);

            }

            //returning list of courses
            return list;
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
//		finally{
//			closeConnection(connection,stmt);
//		}
        return null;
    }

    public void deleteProfessorCourse(int courseId, int ProfessorID) {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        //Establishing the connection
        PreparedStatement stmt = null;
        try {

            stmt=connection.prepareStatement(SQLQueries.DELETE_PROFESSOR_COURSE);
            //Executing query
            int rs = stmt.executeUpdate();
            if(rs>0)
            {
                System.out.println("Course Deselected");
                return;

            }
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }






}