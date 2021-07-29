package com.flipkart.DAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.helper.CloseConnection;
import com.flipkart.helper.DBConnectionHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.flipkart.constant.SQLQueries.DISPLAY_STUDENT;
import static com.flipkart.constant.SQLQueries.DISPLAY_UNAPPROVED;
/*
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public class StudentDaoOperation implements StudentDaoInterface,CloseConnection{
    /**
     * Method to add Course in registered course database
     * @param courseId: Add particular course
     * @param student Object
     */
    public void addCourse(int courseId ,Student student) {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt = connection.prepareStatement(SQLQueries.ADD_COURSE);
            //check ADD_COURSE
            int studentId= student.getStudentId();
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setInt(3, -1);
            stmt.executeUpdate();
            System.out.println("Course with courseId="+courseId+" added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Method to drop Course in registered course database
     * @param courseId: student object containing all the fields
     * @param student Object
     */
    public void dropCourse(int courseId, Student student) {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt=connection.prepareStatement(SQLQueries.DROP_COURSE);
            int studentId= student.getStudentId();
            //check
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            int rs = stmt.executeUpdate();
            if(rs>0)
            {
                System.out.println("Course dropped !");
                return;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Course not found !");
    }
    /**
     * Method to view registered course database
     * @param student Object
     * @return Courses registered by student
     */
    public List<Course> viewRegisteredCourses(Student student) {
        Connection connection= DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt= connection.prepareStatement(SQLQueries.VIEW_REGISTERED_COURSES);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            List<Course> list= new ArrayList<Course>();
            while(rs.next())
            {
                Course course = new Course();
                course.setCourseId(rs.getInt("courseId"));
                list.add(course);
            }
            return list;
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    /**
     * Method to view Grades of a student
     * @param student Object
     * @return Grade card of the student
     */
    public List<RegisteredCourse> viewGrades(Student student) {
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQLQueries.VIEW_REGISTERED_COURSES);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            List<RegisteredCourse> list = new ArrayList<RegisteredCourse>();
            while (rs.next()) {
                RegisteredCourse registeredCourse = new RegisteredCourse();
                registeredCourse.setCourseId(rs.getInt("courseId"));
                registeredCourse.setGrade(rs.getString("grade"));
                registeredCourse.setMarks(rs.getInt("marks"));
                list.add(registeredCourse);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public List<Course> displayRegisteredCourses(Student student) {
        Connection connection= DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            stmt= connection.prepareStatement(SQLQueries.VIEW_SELECTED_COURSES);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            List<Course> list= new ArrayList<Course>();
            while(rs.next())
            {
                Course course = new Course();
                course.setCourseId(rs.getInt("courseId"));
                course.setCourseName(rs.getString("courseName"));
                list.add(course);
            }
            return list;
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    /**
     * Method to get list of Unapproved students
     */
    public void getUnapproved(){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        try {
            //Declaring prepared statement and executing query
            Statement stmt = connection.createStatement();
            //Retrieving data
            ResultSet rs = stmt.executeQuery(DISPLAY_UNAPPROVED);
            System.out.println("Displaying List of Unapproved Students ");
            System.out.println();
            // iterate through the java resultset
            while (rs.next())
            {
                String name = rs.getString("Name");
                int studentId = rs.getInt("studentId");
                System.out.println("Name : "+name + " StudentId : " + studentId);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Method to approve the student by admin
     * @param students to get approved
     *
     */
    public void approveStudent(List<Integer> students){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        for(Integer student : students ){
            try {
                //Declaring prepared statement and executing query
                stmt = connection.prepareStatement(SQLQueries.APPROVE_STUD);
                stmt.setInt(1, student);
                //Executing query
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * Method to display list of students using SQL Command
     */
    public void displayStudents() {
        Connection connection = DBConnectionHelper.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(DISPLAY_STUDENT);
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println("Student Name : " + name);
                String dept=rs.getString("department");
                System.out.println("Department : "+dept);
                int studentId = rs.getInt("studentId");
                System.out.println("Student ID : "+studentId);
                int sem = rs.getInt("sem");
                System.out.println("Semester : "+sem);
                int paymentStatus = rs.getInt("paymentStatus");
                System.out.println("Payment Status : "+paymentStatus);
                int isApproved = rs.getInt("isApproved");
                System.out.println("Approved Status : "+isApproved);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Method to create student using SQL Command
     * @param student object :which contains the details of the student
     * @return student ID
     */
    public int createStudent(Student student) {
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt = null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.INSERT_STUDENT);
//            public static final String  INSERT_STUDENT = "INSERT INTO CRS.student (name, department, sem, paymentStatus, isApproved) values (?,?,?,?,)";
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setInt(3, student.getSem());
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, false);
            //Executing query
            stmt.executeUpdate();
            Statement stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(SQLQueries.GET_RECENT_STUDENT_ID);
            rs.next();
            return rs.getInt("maxStudentId");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
    /**
     * Method to get the details of the student from particular student ID
     * @param UserID :Similar to Student Id
     * @return student Object
     */
    public Student fetchStudent(int UserID){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        Student student = new Student();
        student.setStudentId(UserID);
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.FETCH_STUDENT);
            stmt.setInt(1, UserID);
            //Retrieving data
            ResultSet rs = stmt.executeQuery();
            // iterate through the java resultset
            rs.next();
            String name = rs.getString("name");
            student.setName(name);
            String department = rs.getString("department");
            student.setDepartment(department);
            int sem = rs.getInt("sem");
            student.setSem(sem);
            boolean paymentStatus=rs.getBoolean("paymentStatus");
            student.setPaymentStatus(paymentStatus);
            boolean isApproved=rs.getBoolean("isApproved");
            student.setApproved(isApproved);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return student;
    }
    /**
     * Method to get the Payment Status
     * @param studentId :
     * @return whether payment is done or not
     */
    public boolean getPaymentStatus(int studentId){
        Connection connection= DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        boolean stat=false;
        try {
            stmt= connection.prepareStatement(SQLQueries.GET_PAY_STATUS);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stat=rs.getBoolean("paymentStatus");
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stat;
    }
    /**
     * Method to set the payment status of particular student to true.
     * @param studentId: Of a particular id
     */
    public void setPaymentStatus(int studentId){
        //Establishing the connection
        Connection connection = DBConnectionHelper.getConnection();
        PreparedStatement stmt= null;
        try {
            //Declaring prepared statement and executing query
            stmt = connection.prepareStatement(SQLQueries.SET_PAY_STATUS);
            stmt.setInt(1, studentId);
            //Executing query
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
