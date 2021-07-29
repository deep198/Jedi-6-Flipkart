package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author JEDI-Group05
 * Implementations of Professor Operations
 *
 */
public class ProfessorOperation implements ProfessorInterface{
    /**
     * Method to select courses
     * @param courseId Id of course selected by professor
     * @param professor Object storing details of a Professor
     */
    public void selectCourse(int courseId, Professor professor) {
        CourseInterface newCourse1=new CourseOperation();
        newCourse1.selectprofcourse(courseId , professor.getProfessorId());
    }
    /**
     * Method to display available courses Professor teaches
     * @param professor Object storing details of a Professor
     */
    @Override
    public void displayAvailableCoursesProfessor(Professor professor) {
        CourseInterface newCourse=new CourseOperation();
        newCourse.displayCoursesProfessor();
    }
    /**
     * Method to fetch Professor details
     * @param UserID Id of professor to be fetched
     * @return Professor Object storing details of a Professor
     */
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
    /**
     * Method to delete particular course which Professor teaches
     * @param courseId Id of course deselected by professor
     * @param prof Object storing details of a Professor
     */
    public void deleteProfessorCourse(int courseId,Professor prof)
    {
        CourseInterface newCourse1=new CourseOperation();
        newCourse1.deleteprofcourse(courseId , prof.getProfessorId());
    }
    /**
     * Method to display courses Professor teaches
     * @param professor Object storing details of a Professor
     */
    public void displaySelectedCoursesProfessor(Professor professor)
    {
        CourseInterface newCourse1=new CourseOperation();
        newCourse1.displaySelectedProfCourse(professor);
    }
}