package com.flipkart.DAO;
import com.flipkart.bean.Student;
import com.flipkart.constant.PaymentMode;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;
import java.sql.*;
/*
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public class PaymentDaoOperation implements PaymentDaoInterface {
    /**
     * Method to show registration receipt after the payment
     *
     * @param student object :which contains the details of the student
     */
    public void showRegistrationReceipt(Student student) {
        int studentId = student.getStudentId();
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
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
            System.out.println("Name : " + student.getStudentId() + " paid " + amount + " via " + PaymentMode.values()[paymentMode - 1]);
            System.out.println("Reference ID: " + referenceId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method to show the amount to be paid by a student of particular semester
     *
     * @param student             object :which contains the details of the student
     * @param payableAmount:Fixed amount for the particular semester
     * @param paymentMode             mode:To select the mode of payment whether card, netbanking or wallet
     * @return Reference id of the payment done.
     */
    public int makeRegistrationEntry(Student student, double payableAmount, int paymentMode) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.GENERATE_RECEIPT);
            int studentId = student.getStudentId();
            // stmt.setInt(1, userId);
            stmt.setInt(1, studentId);
            stmt.setInt(2, student.getSem());
            stmt.setDouble(3, payableAmount);
            stmt.setInt(4, paymentMode);
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