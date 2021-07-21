package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;

public class UserOperation implements UserInterface{

    @Override
    public User validateUser(String username, String password) throws InvalidLoginException {
        return null;
    }

    @Override
    public Student fetchStudent(String userName) {
        return null;
    }

    @Override
    public Admin fetchAdmin(String userName) {
        return null;
    }

    @Override
    public Professor fetchProfessor(String userName) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void createStudent(Student student) {

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
}
