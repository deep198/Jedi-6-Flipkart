package com.flipkart.DAO;
import com.flipkart.bean.Admin;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constant.SQLQueries.DISPLAY_ADMIN;
public class AdminDaoOperation implements AdminDaoInterface {
    private static volatile AdminDaoOperation instance = null;
    /**
     * Default Constructor
     */
    public AdminDaoOperation(){
    }
    /**
     * Method to make AdminDaoOperation Singleton
     * @return
     */
    public static AdminDaoOperation getInstance()
    {
        if(instance == null)
        {
            synchronized(AdminDaoOperation.class){
                instance = new AdminDaoOperation();
            }
        }
        return instance;
    }
    @Override
    public int createAdmin(Admin admin) {
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
            Statement stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(SQLQueries.GET_RECENT_ADMIN_ID);
            rs.next();
            return rs.getInt("maxAdminId");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    @Override
    public List<Admin> displayAdmins() {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        List<Admin> admins = new ArrayList<>();
        try {
            //Declaring prepared statement and executing query
            Statement stmt = connection.createStatement();
            //Retrieving data
            ResultSet rs = stmt.executeQuery(DISPLAY_ADMIN);
            // iterate through the java resultset
            System.out.println("ID    \tName");
            while (rs.next()) {
                String name = rs.getString("adminName");
                int adminId = rs.getInt("adminId");
                Admin admin = new Admin();
                admin.setAdminId(adminId);
                admin.setName(name);
                admins.add(admin);
                System.out.println(adminId+"  \t"+name);
            }
            System.out.println();
            return admins;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  admins;
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