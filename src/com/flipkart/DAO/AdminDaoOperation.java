package com.flipkart.DAO;

import com.flipkart.bean.Admin;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.*;

import static com.flipkart.constant.SQLQueries.DISPLAY_ADMIN;

public class AdminDaoOperation implements AdminDaoInterface {

    @Override
    public void createAdmin(Admin admin) {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;

        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_ADMIN);

            String name = admin.getName();

            // stmt.setInt(1, userId);
            stmt.setString(1, name);

            //Executing query
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void displayAdmins() {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();

        try {
            //Declaring prepared statement and executing query
            Statement stmt = connection.createStatement();

            //Retrieving data
            ResultSet rs = stmt.executeQuery(DISPLAY_ADMIN);

            // iterate through the java resultset
            while (rs.next()) {
                String name = rs.getString("adminName");
                System.out.println("Name : " + name);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Admin fetchAdmin(int userID) {
           Connection connection = DBConnectionHelper.getConnection();
            PreparedStatement stmt= null;
            Admin admin = new Admin();
            admin.setAdminId(userID);
            try {
                //Declaring prepared statement and executing query
                stmt = connection.prepareStatement(SQLQueries.FETCH_ADMIN);
                stmt.setInt(1, userID);
                //Retrieving data
                ResultSet rs = stmt.executeQuery();
                rs.next();
                // iterate through the java resultset
                String name = rs.getString("adminName");
                admin.setName(name);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return admin;
        }
    }
