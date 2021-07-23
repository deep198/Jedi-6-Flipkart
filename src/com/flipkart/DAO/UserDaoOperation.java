package com.flipkart.DAO;

import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constant.SQLQueries.VALIDATE_USER;

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

    @Override
    public User validateUser(int userId, String password) {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;

        try {
            //Declaring prepared statement
            stmt=connection.prepareStatement(VALIDATE_USER);
            stmt.setInt(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next() )
            {
                User checkeduser = new User();
                checkeduser.setUserName( rs.getString("userName") );
                checkeduser.setUserRole(rs.getString("userRole"));

                return checkeduser;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//
        return null;
    }

}
