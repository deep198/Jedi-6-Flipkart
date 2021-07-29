package com.flipkart.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionHelper {

    private static Connection connection = null;

    public static void main(String args[]) {
        Connection connection = getConnection();
        System.out.println("Connection received successfully!!");

    }



    public static Connection getConnection() {

        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
//                InputStream inputStream = DBConnectionHelper.class.getClassLoader().getResourceAsStream("configuration.properties");
//                prop.load(inputStream);
//                String driver = prop.getProperty("driver");
//                String url = prop.getProperty("url");
//                String user = prop.getProperty("user");
//                String password = prop.getProperty("password");
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306";
                String user = "root";
                String password = "pass";
                System.out.println("NO ERROR");
                Class.forName(driver);
                System.out.println("NO ERROR2");
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
            }
            return connection;
        }


    }

}
