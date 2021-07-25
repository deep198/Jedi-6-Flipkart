package com.flipkart.business;
import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProfessorOperation implements ProfessorInterface{
    public void selectCourse(int courseId, Professor professor) {
        CourseInterface newCourse1=new CourseOperation();
        newCourse1.selectprofcourse(courseId , professor.getProfessorId());
    }
    @Override
    public void displayAvailableCoursesProfessor(Professor professor) {
        CourseInterface newCourse=new CourseOperation();
        newCourse.displayCoursesProfessor();
    }
    public Professor fetchProfessor(int UserID){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        Professor professor = new Professor();
        professor.setProfessorId(UserID);
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.FETCH_PROF);
            stmt.setInt(1, UserID);
            //Retrieving data
            ResultSet rs = stmt.executeQuery();
            rs.next();
            // iterate through the java resultset
            String name = rs.getString("name");
            professor.setName(name);
            String dept = rs.getString("department");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return professor;
    }
    public void deleteProfessorCourse(int courseId,Professor prof)
    {
        System.out.println("Entered deleteprofcourse in professoroperation");
        CourseInterface newCourse1=new CourseOperation();
        newCourse1.deleteprofcourse(courseId , prof.getProfessorId());
    }
    public void displaySelectedCoursesProfessor(Professor professor)
    {
        CourseInterface newCourse1=new CourseOperation();
        newCourse1.displaySelectedProfCourse(professor);
    }
}