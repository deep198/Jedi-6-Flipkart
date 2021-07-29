package com.flipkart.business;
import com.flipkart.DAO.*;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.ArrayList;
import java.util.List;
import static com.flipkart.constant.UserRole.ADMIN;
import static com.flipkart.constant.UserRole.PROFESSOR;
/**
 *
 * @author JEDI-Group05
 * Implementation of Admin Operations
 *
 */
public class AdminOperation implements AdminInterface{
    private static volatile AdminOperation instance = null;
    public AdminOperation()
    {
    }
    /**
     * Method to make AdminOperation Singleton
     */
    public static AdminOperation getInstance()
    {
        if(instance == null)
        {
            synchronized(AdminOperation.class){
                instance = new AdminOperation();
            }
        }
        return instance;
    }
    AdminDaoInterface adminDaoOperation =AdminDaoOperation.getInstance();
    AdminDaoInterface newAdmin = new AdminDaoOperation();
    /**
     * Method to create Professor
     * @param professor Object storing professor details
     * @return user Id
     */
    @Override
    public int createProfessor(Professor professor) {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        int userId = newProfessor.createProfessor(professor);
        UserDaoInterface userDaoOperation = new UserDaoOperation();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(professor.getPassword());
        user.setUserRole(PROFESSOR);
        user.setUserName(professor.getUserName());
        userDaoOperation.createUser(user);
        return userId;
    }
    /**
     * Method to create Admin
     * @param admin Object storing admin details
     * @return user Id
     */
    @Override
    public int createAdmin(Admin admin) {
        UserDaoInterface userDaoOperation = new UserDaoOperation();
        int userId = newAdmin.createAdmin(admin);
        User user = new User();
        user.setUserName(admin.getUserName());
        user.setUserRole(ADMIN);
        user.setUserId(userId);
        user.setPassword(admin.getPassword());
        user.setUserName(admin.getUserName());
        userDaoOperation.createUser(user);
        return userId;
    }
    /**
     * Method to display Student details
     * @return
     */
    @Override
    public List<String> displayStudents() {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        List<Student> students = studentDaoOperation.displayStudents();
        List<String> studentDetails = new ArrayList<>();
        for (Student student : students) {
            studentDetails.add(student.toString());
        }

        return studentDetails;
    }
    /**
     * Method to display Professor details
     */
    @Override
    public List<String> displayProfessors() {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        List<Professor> professors = newProfessor.displayProfessors();
        List<String> professorInformation = new ArrayList<>();
        for (Professor professor : professors) {
            professorInformation.add(professor.toString());
        }
        return professorInformation;
    }
    /**
     * Method to fetch Admin details
     * @param userID UserId of admin to fetch details
     * @return Admin Object storing admin details
     */
    public Admin fetchAdmin(int userID){
        AdminDaoInterface adminDao = new AdminDaoOperation();
        Admin admin=adminDao.fetchAdmin(userID);
        return admin;
    }
    /**
     * Method to display Admin details
     */
    @Override
    public List<String> displayAdmins() {
        AdminDaoInterface newAdmin = new AdminDaoOperation();
        List<Admin> admins = newAdmin.displayAdmins();
        List<String> adminDetails = new ArrayList<>();
        for (Admin admin : admins) {
            adminDetails.add(admin.toString());
        }
        return adminDetails;
    }
    /**
     * Method to get unapproved Students
     */
    @Override
    public List<Integer> showunapprovedStudents(){
        StudentDaoInterface studentDao=new StudentDaoOperation();
        return studentDao.getUnapproved();
    }
    /**
     * Method to get approve studnts' registration
     * @param students of Student Objects
     */
    @Override
    public void approveStudent(List<Integer> students){
        StudentDaoInterface studentDao=new StudentDaoOperation();
        studentDao.approveStudent(students);
    }
}