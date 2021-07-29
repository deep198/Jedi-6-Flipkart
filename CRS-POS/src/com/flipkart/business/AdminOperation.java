package com.flipkart.business;
import com.flipkart.DAO.*;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
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
    AdminDaoInterface newAdmin=new AdminDaoOperation();
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
     */
    @Override
    public void displayStudents() {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        studentDaoOperation.displayStudents();
    }
    /**
     * Method to display Professor details
     */
    @Override
    public void displayProfessors() {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        newProfessor.displayProfessors();
    }
    /**
     * Method to fetch Admin details
     * @param userID UserId of admin to fetch details
     * @return Admin Object storing admin details
     */
    public Admin fetchAdmin(int userID){
        AdminDaoInterface adminDao= new AdminDaoOperation();
        Admin admin=adminDao.fetchAdmin(userID);
        return admin;
    }
    /**
     * Method to display Admin details
     */
    @Override
    public void displayAdmins() {
        AdminDaoInterface newAdmin=new AdminDaoOperation();
        newAdmin.displayAdmins();
    }
    /**
     * Method to get unapproved Students
     */
    @Override
    public void showunapprovedStudents(){
        StudentDaoInterface studentDao=new StudentDaoOperation();
        studentDao.getUnapproved();
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