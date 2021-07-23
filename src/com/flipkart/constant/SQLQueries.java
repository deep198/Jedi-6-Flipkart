package com.flipkart.constant;

public class SQLQueries {

    public static final String INSERT_USER = "INSERT INTO CRS.user VALUES (?,?,?,?)";
    public static final String VALIDATE_USER="SELECT userName, userRole FROM CRS.user WHERE userId=? AND password=?";
}
