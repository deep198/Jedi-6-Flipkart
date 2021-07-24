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
    public static final String DISPLAY_COURSES= "SELECT * FROM CRS.course WHERE sem = ? AND branch =?";
    public static final String INSERT_COURSE="INSERT INTO CRS.course VALUES (?,?,?,?,?,?,?)";
    public static final String DELETE_COURSE="DELETE FROM CRS.course WHERE courseId = " ;
    public static final String DISPLAY_COURSES_PROFESSOR= "SELECT * FROM CRS.course WHERE professorId=-1" ;
    //public static final String SELECT_COURSE="UPDATE course SET ProfessorID = ? WHERE CourseID=? ";
    public static final String DELETE_PROFESSOR_COURSE="UPDATE CRS.course SET professorId = -1 WHERE professorId = ? AND courseId=?";
    public static final String DISPLAY_UNAPPROVED = "SELECT * FROM CRS.student WHERE isApproved=FALSE";
    public static final String APPROVE_STUD = "UPDATE CRS.student SET isApproved=TRUE where studentId=?";
    public static final String VIEW_GRADES = "SELECT * FROM CRS.registered_course where studentId = ?";
    public static final String UPLOAD_GRADES = "UPDATE CRS.registered_course SET marks = ? AND grade = ? WHERE studentId = ? AND courseId = ?";
    public static final String SELECT_PROFESSOR_COURSE= "UPDATE CRS.course SET professorId = ? WHERE courseId=? AND professorId=-1 ";
    public static final String GET_RECENT_PROF_ID = "SELECT MAX(professorId) as maxProfId FROM CRS.professor";
    public static final String DISPLAY_STUDENT = "SELECT * FROM CRS.student";
    public static final String FETCH_STUDENT = "SELECT * FROM CRS.student WHERE studentId=?";
    public static final String FETCH_ADMIN = "SELECT * FROM CRS.admin WHERE adminId=?";
    public static final String FETCH_PROF = "SELECT * FROM CRS.professor WHERE professorId=?";

}
