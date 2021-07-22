package com.flipkart.DAO;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.HashMap;
import java.util.Map;

import static com.flipkart.constant.UserRole.*;

public class TemporaryDataStore {

    private static Map<String, User> userCredentials = new HashMap<>();
    private static Map<String, Professor> professors = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, Admin> admins = new HashMap<>();


    public void addStudent(Student student) {
        students.put(student.getUserName(), student);
        User user = new User();
        user.setUserName(student.getUserName());
        user.setPassword(student.getPassword());
        user.setUserRole(STUDENT);
        userCredentials.put(user.getUserName(), user);
    }

    public User getUser(String username) {
        return userCredentials.get(username);
    }

    public void addProfessor(Professor professor) {
        User user = new User();
        user.setUserRole(PROFESSOR);
        user.setUserName(professor.getUserName());
        user.setPassword(professor.getPassword());
        userCredentials.put(user.getUserName(), user);
        professors.put(professor.getUserName(), professor);
    }

    public void addAdmin(Admin admin) {
        User user = new User();
        user.setUserRole(ADMIN);
        user.setUserName(admin.getUserName());
        user.setPassword(admin.getPassword());
        userCredentials.put(user.getUserName(), user);
        admins.put(admin.getUserName(), admin);
    }

    public Student getStudent(String userName) {
        return students.get(userName);
    }

    public Admin getAdmin(String userName) {
        return admins.get(userName);
    }

    public Professor getProfessor(String userName) {
        return professors.get(userName);
    }

    public void updatePassword(String username, String oldPswd, String newPswd) {
        userCredentials.get(username).setPassword(newPswd);
    }
}
