package com.flipkart.DAO;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.CloseConnection;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
public class RegisteredCourseDaoOperation implements RegisteredCourseDaoInterface, CloseConnection{
    //Initializing the logger
    // private static Logger logger = Logger.getLogger(RegisteredCourseDaoOperation.class);
    // fetch grades of students against courseId and studentId

    public List<RegisteredCourse> displayGradeCard(Student student) {
        //Establishing the connection
        Connection connection= DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            //Declaring prepared statement and executing query
            stmt= connection.prepareStatement(SQLQueries.VIEW_GRADES);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            List<RegisteredCourse> list= new ArrayList<RegisteredCourse>();
            //Creating ArrayList of course
            while(rs.next())
            {
                RegisteredCourse registeredCourse = new RegisteredCourse();
                registeredCourse.setCourseId(rs.getInt("courseId"));
                registeredCourse.setGrade(rs.getString("grade"));
                list.add(registeredCourse);
            }
            //returning list of courses
            return list;
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // upload grades against a studentId and courseId
    @Override
    public void uploadGrades(int studentId, int courseId, String grade, int marks) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt=null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.UPLOAD_GRADES);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setString(3, grade);
            stmt.setInt(4, marks);
            //Executing query
            stmt.executeUpdate();
            System.out.println("Grade uploaded");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}