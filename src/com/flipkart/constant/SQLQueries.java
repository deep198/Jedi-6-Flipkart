package com.flipkart.constant;

public class SQLQueries {

    public static final String INSERT_USER = "INSERT INTO CRS.user VALUES (?,?,?,?)";
    public static final String VALIDATE_USER="SELECT userName, userRole FROM CRS.user WHERE userId=? AND password=?";
    public static final String UPDATE_USER="UPDATE CRS.user SET username=?,password=?, userRole=? WHERE userId=?";
    public static final String ADD_COURSE="INSERT INTO CRS.registered_course VALUES (?,?,?)";
    public static final String DROP_COURSE="DELETE FROM CRS.registered_course WHERE studentId=? AND courseId=?";
    public static final String VIEW_SELECTED_COURSES="SELECT CRS.course.courseId, CRS.course.courseName\r\n" +
					"FROM CRS.course \r\n" + "INNER JOIN CRS.registered_course \r\n" + "ON CRS.registered_course.courseId = CRS.course.courseId\r\n" + "WHERE CRS.registered_course.studentId= ?";
    public static final String INSERT_ADMIN = "INSERT INTO CRS.admin (adminName) VALUES (?)";
    public static final String DISPLAY_ADMIN = "SELECT * FROM CRS.admin";
    public static final String INSERT_PROF = "INSERT INTO CRS.professor (name, department) VALUES (?,?)";
    public static final String DISPLAY_PROF = "SELECT * FROM CRS.professor";


}
