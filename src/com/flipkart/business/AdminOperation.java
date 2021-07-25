package com.flipkart.business;

import com.flipkart.DAO.*;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

import java.util.List;

import static com.flipkart.constant.UserRole.ADMIN;
import static com.flipkart.constant.UserRole.PROFESSOR;

public class AdminOperation implements AdminInterface{

    AdminDaoInterface newAdmin=new AdminDaoOperation();

    @Override
    public int createProfessor(Professor professor) {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        int userId = newProfessor.createProfessor(professor);
        UserDaoInterface userDaoOperation = new UserDaoOperation();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(professor.getPassword());
        user.setUserRole(PROFESSOR);
        userDaoOperation.createUser(user);
        return userId;
    }
    @Override
    public int createAdmin(Admin admin) {
        int userId = newAdmin.createAdmin(admin);
        User user = new User();
        user.setUserName(admin.getUserName());
        user.setUserRole(ADMIN);
        user.setUserId(admin.getUserId());
        user.setPassword(admin.getPassword());
        return userId;
    }

    @Override
    public void displayStudents() {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        studentDaoOperation.displayStudents();
    }
    @Override
    public void displayProfessors() {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        newProfessor.displayProfessors();
    }


    public Admin fetchAdmin(int userID){
        AdminDaoInterface adminDao= new AdminDaoOperation();
        Admin admin=adminDao.fetchAdmin(userID);
        return admin;
    }

    @Override
    public void displayAdmins() {
        AdminDaoInterface newAdmin=new AdminDaoOperation();
        newAdmin.displayAdmins();
    }

    @Override
    public void showunapprovedStudents(){
        StudentDaoInterface studentDao=new StudentDaoOperation();
        studentDao.getUnapproved();
    }
    @Override
    public void approveStudent(List<Integer> students){
        StudentDaoInterface studentDao=new StudentDaoOperation();
        studentDao.approveStudent(students);
    }
}