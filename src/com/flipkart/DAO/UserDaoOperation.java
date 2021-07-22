package com.flipkart.DAO;

import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoOperation implements UserDaoInterface{

    @Override
    public void createUser(User user)  {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;

        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_USER);
            int userId = user.getUserId();
            String username = user.getUserName();
            String password = user.getPassword();
            String role = user.getUserRole();

            stmt.setInt(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, role);

            //Executing query
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
