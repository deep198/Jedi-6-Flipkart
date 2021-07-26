package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.helper.DBConnectionHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CourseDaoOperation implements CourseDaoInterface {

    private static final Logger logger = Logger.getLogger(CourseDaoOperation.class.getName());

    //List of Courses available to select for a student of particular branch and semester
    public List<Course> displayCourses(Student student) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        //Declaring prepared statement and executing query
        PreparedStatement stmt = null;
        try {
            int semester = student.getSem();
            String branch = student.getDepartment();
            stmt = connection.prepareStatement(SQLQueries.DISPLAY_COURSES);
            stmt.setInt(1, semester);
            stmt.setString(2, branch);
            ResultSet rs = stmt.executeQuery();
            List<Course> list = new ArrayList<Course>();
            //Creating ArrayList of Course
            while (rs.next()) {
                Course course = new Course();
                course.setDepartment(branch);
                course.setSem(semester);
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                course.setCredits(rs.getInt("credits"));
                course.setProfessorId(rs.getInt("professorId"));
                list.add(course);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    @Override
    public List<Course> selectedprofcourse(int professorid)
    {
        Connection connection = DBConnectionHelper.getConnection();
        //Declaring prepared statement and executing query
        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement(SQLQueries.VIEW_PROFESSOR_SELECTED_COURSE);
            stmt.setInt(1, professorid);
            ResultSet rs = stmt.executeQuery();
            List<Course> list = new ArrayList<Course>();
            //Creating ArrayList of Course
            while (rs.next()) {
                Course course = new Course();
                course.setDepartment(rs.getString("department"));
                course.setSem(rs.getInt("sem"));
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                course.setCredits(rs.getInt("credits"));
                course.setProfessorId(rs.getInt("professorId"));
                list.add(course);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    // Insert a new course in the database
    public void insertCourse(Course course) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        //Establishing the connection
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_COURSE);
            int courseID = course.getCourseId();
            String title = course.getCourseName();
            int semester = course.getSem();
            String branch = course.getDepartment();
            int credits = course.getCredits();
            stmt.setInt(1, courseID);
            stmt.setString(2, title);
            stmt.setInt(3, -1);
            stmt.setString(4, branch);
            stmt.setInt(5, semester);
            stmt.setInt(6, credits);
            stmt.setInt(7, 0);
            //Executing query
            stmt.executeUpdate();
            logger.info("Course added!");
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
            stmt = connection.prepareStatement(SQLQueries.DELETE_COURSE + courseId);
            //Executing query
            int rs = stmt.executeUpdate();
            if (rs > 0) {
                logger.info("Course with courseId " + courseId + " deleted !");
                return;
            }
            throw new CourseNotFoundException();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Course> displayCoursesProfessor() {
        Connection connection = DBConnectionHelper.getConnection();
        //Declaring prepared statement and executing query
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQLQueries.DISPLAY_COURSES_PROFESSOR);
            ResultSet rs = stmt.executeQuery();
            List<Course> list = new ArrayList<Course>();
            //Creating ArrayList of courses
            while (rs.next()) {
                Course course = new Course();
                course.setDepartment(rs.getString("department"));
                course.setSem(rs.getInt("sem"));
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                list.add(course);
            }
            //returning list of courses
            return list;
        } catch (SQLException ex) {
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
            stmt = connection.prepareStatement(SQLQueries.DELETE_PROFESSOR_COURSE);
            stmt.setInt(1, ProfessorID);
            stmt.setInt(2, courseId);
            //Executing query
            int rs = stmt.executeUpdate();
            if (rs > 0) {
                logger.info("Course Deselected!!");
                return;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void selectCourse(int courseId, int professorid) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.SELECT_PROFESSOR_COURSE);
            stmt.setInt(1, professorid);
            stmt.setInt(2, courseId);
            //Executing query
            stmt.executeUpdate();
            logger.info("Course with courseId=" + courseId + " selected to teach!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void incrementEnrolledStudents(int courseId) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INCREMENT_ENROLLED_STUDENTS);
            stmt.setInt(1,courseId);
            //Executing query
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void decrementEnrolledStudents(int courseId) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.DECREMENT_ENROLLED_STUDENTS);
            stmt.setInt(1,courseId);
            //Executing query
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getEligibleCoursesToSelectForStudent(Student student) {
        Connection connection = DBConnectionHelper.getConnection();
        //Declaring prepared statement and executing query
        PreparedStatement stmt = null;
        try {
            int semester = student.getSem();
            String branch = student.getDepartment();
            stmt = connection.prepareStatement(SQLQueries.DISPLAY_COURSES);
            stmt.setInt(1, semester);
            stmt.setString(2, branch);
            ResultSet rs = stmt.executeQuery();
            List<Course> list = new ArrayList<Course>();
            while (rs.next()) {
                if (rs.getInt("studentsEnrolled") >= 10) {
                    continue;
                }
                Course course = new Course();
                course.setDepartment(branch);
                course.setSem(semester);
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                course.setProfessorId(rs.getInt("professorId"));
                list.add(course);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}