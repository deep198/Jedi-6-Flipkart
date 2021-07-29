package com.flipkart.DAO;

import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.IncorrectOldPassword;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constant.SQLQueries.UPDATE_USER;
import static com.flipkart.constant.SQLQueries.VALIDATE_USER;

public class UserDaoOperation implements UserDaoInterface{
    private static volatile UserDaoOperation instance=null;

    public UserDaoOperation()
    {

    }

    /**
     * Method to make UserDaoOperation Singleton
     * @return
     */
    public static UserDaoOperation getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(UserDaoOperation.class){
                instance=new UserDaoOperation();
            }
        }
        return instance;
    }

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
    public void updatePassword(int userId, String oldPswd, String newPswd) throws IncorrectOldPassword {

        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.GET_PASSWORD);
            stmt.setInt(1, userId);
            //Executing query
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String pswd = rs.getString("password");
            System.out.println("Password in Database" + pswd);
            if (pswd.equals(oldPswd)) {
                stmt = connection.prepareStatement(SQLQueries.UPDATE_PASSWORD);
                stmt.setString(1, newPswd);
                stmt.setInt(2, userId);
                stmt.executeUpdate();
            } else {
                throw new IncorrectOldPassword();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public User validateUser(int userId, String password) throws InvalidLoginException, NotApprovedException, UserNotFoundException {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;

        try {
            stmt = connection.prepareStatement(SQLQueries.GET_PASSWORD);
            stmt.setInt(1, userId);
            //Executing query
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String pswd = rs.getString("password");
            String userRole = rs.getString("userRole");
            if (userRole.equals("STUDENT")) {
                stmt = connection.prepareStatement(SQLQueries.GET_APPROVED_STATUS);
                stmt.setInt(1, userId);
                rs = stmt.executeQuery();
                rs.next();
                Boolean approvedStatus = rs.getBoolean("isApproved");
                if (! approvedStatus) {
                    throw new NotApprovedException();
                }
            }

            if (!pswd.equals(password)) {
                throw new InvalidLoginException();
            }
            //Declaring prepared statement
            stmt=connection.prepareStatement(VALIDATE_USER);
            stmt.setInt(1, userId);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if(rs.next())
            {
                User checkeduser = new User();
                checkeduser.setUserName( rs.getString("userName") );
                checkeduser.setUserRole(rs.getString("userRole"));
                checkeduser.setUserId(rs.getInt("userId"));
                return checkeduser;
            } else {
                throw new UserNotFoundException();
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Illegal operation on empty result set.")) throw new UserNotFoundException();
            System.out.println(ex.getMessage());
        }
        throw new UserNotFoundException();
    }

    @Override
    public void updateUser(User user) {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;

        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(UPDATE_USER);
            int userId= user.getUserId();
            String userName = user.getUserName();
            String password= user.getPassword();
            String roleName = user.getUserRole();

            stmt.setString(1, userName);
            stmt.setString(2, password);
            stmt.setString(3, roleName);
            stmt.setInt(4, userId);

            //Executing query
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
