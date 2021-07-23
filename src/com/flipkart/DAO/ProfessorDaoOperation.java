package com.flipkart.DAO;
import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;
import java.sql.*;
import static com.flipkart.constant.SQLQueries.DISPLAY_PROF;
public class ProfessorDaoOperation implements ProfessorDaoInterface {
    @Override
    public void createProfessor(Professor professor){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_PROF);
            String name = professor.getName();
            String dept = professor.getDepartment();
            // stmt.setInt(1, userId);
            stmt.setString(1, name);
            stmt.setString(2, dept);
            //Executing query
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
}