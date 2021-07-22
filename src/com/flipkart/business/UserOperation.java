package com.flipkart.business;

import com.flipkart.DAO.TemporaryDataStore;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;

import java.util.HashMap;
import java.util.Map;

public class UserOperation implements UserInterface{

    TemporaryDataStore dataAccess = new TemporaryDataStore();

    @Override
    public User validateLogin(String username, String password) throws InvalidLoginException {
        User user = dataAccess.getUser(username);
        System.out.println("in validate");
        System.out.println("User fetched " + user.toString());
        if (user.getPassword().equals(password)) return user;
        //TODO
        return null;
    }

    @Override
    public Student fetchStudent(String userName) {
        return dataAccess.getStudent(userName);
    }

    @Override
    public Admin fetchAdmin(String userName) {
        return dataAccess.getAdmin(userName);
    }

    @Override
    public Professor fetchProfessor(String userName) {
        return dataAccess.getProfessor(userName);
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void createStudent(Student student) {

        dataAccess.addStudent(student);

    }

    @Override
    public void createProfessor(Professor professor) {

    }

    @Override
    public void createAdmin(Admin newAdmin) {

    }

    @Override
    public void displayStudents() {

    }

    @Override
    public void displayProfessors() {

    }

    @Override
    public void displayAdmins() {

    }

    @Override
    public void updatePassword(String username, String oldPswd, String newPswd) {
        dataAccess.updatePassword(username, oldPswd, newPswd);

    }
}
