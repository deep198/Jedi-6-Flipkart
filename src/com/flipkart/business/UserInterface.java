package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;

public interface UserInterface {

    User validateLogin(String username, String password)throws InvalidLoginException;
    Student fetchStudent(String userName);
    Admin fetchAdmin(String userName);
    Professor fetchProfessor(String userName);
    void createUser(User user);

    void createStudent(Student student);

    void createProfessor(Professor professor);

    void createAdmin(Admin newAdmin);

    void displayStudents();

    void displayProfessors();

    void displayAdmins();

    void updatePassword(String username, String oldPswd, String newPswd);
}
