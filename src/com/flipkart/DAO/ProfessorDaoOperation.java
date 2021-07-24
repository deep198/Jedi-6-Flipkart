package com.flipkart.DAO;
import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;
import java.sql.*;
import static com.flipkart.constant.SQLQueries.DISPLAY_PROF;
public class ProfessorDaoOperation implements ProfessorDaoInterface {
    @Override
    public int createProfessor(Professor professor) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;

        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_PROF);
            String name = professor.getName();
            String dept = professor.getDepartment();
            stmt.setString(1, name);
            stmt.setString(2, dept);
            //Executing query
            stmt.executeUpdate();
            Statement stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(SQLQueries.GET_RECENT_PROF_ID);
            rs.next();
            return rs.getInt("maxProfId");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    @Override
    public void displayProfessors(){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        try {
            //Declaring prepared statement and executing query
            Statement stmt = connection.createStatement();
            //Retrieving data
            ResultSet rs = stmt.executeQuery(DISPLAY_PROF);
            // iterate through the java resultset
            while (rs.next())
            {
                String name = rs.getString("Name");
                System.out.println("Name : "+name);
                String dept = rs.getString("Department");
                System.out.println("Department : "+dept);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
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
}