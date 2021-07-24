package com.flipkart.business;

import com.flipkart.DAO.*;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

import java.util.List;

import static com.flipkart.constant.UserRole.PROFESSOR;

public class AdminOperation implements AdminInterface{

    AdminDaoInterface newAdmin=new AdminDaoOperation();

    @Override
    public void createProfessor(Professor professor) {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        int userId = newProfessor.createProfessor(professor);
        UserDaoInterface userDaoOperation = new UserDaoOperation();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(professor.getPassword());
        user.setUserRole(PROFESSOR);
        userDaoOperation.createUser(user);
    }
    @Override
    public void createAdmin(Admin admin) {

        newAdmin.createAdmin(admin);
    }
    @Override
    public void displayStudents() {
        //   dataAccess.displayStudents();
        System.out.println("Display Student");
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