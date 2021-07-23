package com.flipkart.business;

import com.flipkart.DAO.*;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;

import java.util.List;

public class AdminOperation implements AdminInterface{

    AdminDaoInterface newAdmin=new AdminDaoOperation();

    @Override
    public void createProfessor(Professor professor) {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        newProfessor.createProfessor(professor);
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

    @Override
    public Admin fetchAdmin(int userId) {
        Admin admin = new Admin();
        admin.setName("RandAdmin");
        return admin;
//        return newAdmin.fetchAdmin();
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