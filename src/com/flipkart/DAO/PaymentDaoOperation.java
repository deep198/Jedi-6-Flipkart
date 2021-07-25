package com.flipkart.DAO;

import com.flipkart.bean.Student;
import com.flipkart.constant.PaymentMode;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.*;

public class PaymentDaoOperation implements PaymentDaoInterface {

    @Override
    public void showRegistrationReceipt(Student student) {
        int studentId= student.getStudentId();
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.SHOW_RECEIPT);
            stmt.setInt(1, studentId);
            //Retrieving data
            ResultSet rs = stmt.executeQuery();
            rs.next();
            // iterate through the java resultset
            double amount = rs.getDouble("payableAmount");
            int referenceId = rs.getInt("referenceId");
            int paymentMode = rs.getInt("paymentMode");
            System.out.println("Name : "+student.getStudentId()+" paid "+ amount + " via " + PaymentMode.values()[paymentMode - 1]);
            System.out.println("Reference ID: " + referenceId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int makeRegistrationEntry(Student student, double payableAmount, int paymentMode){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.GENERATE_RECEIPT);
            int studentId = student.getStudentId();
            // stmt.setInt(1, userId);
            stmt.setInt(1, studentId);
            stmt.setInt(2,student.getSem());
            stmt.setDouble(3,payableAmount);
            stmt.setInt(4,paymentMode);
            //Executing query
            stmt.executeUpdate();
            Statement stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(SQLQueries.GET_REFERENCE_ID);
            rs.next();
            return rs.getInt("maxreferenceId");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
}
