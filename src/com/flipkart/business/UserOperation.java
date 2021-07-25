package com.flipkart.business;

import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.DAO.UserDaoInterface;
import com.flipkart.DAO.UserDaoOperation;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;

public class UserOperation implements UserInterface{

    UserDaoInterface userDaoOperation = new UserDaoOperation();
    StudentDaoInterface studentDaoOperation = new StudentDaoOperation();


    @Override
    public User validateLogin(int userId, String password) throws InvalidLoginException {
        return userDaoOperation.validateUser(userId, password);
    }

    @Override
    public void createUser(User user) {
        userDaoOperation.createUser(user);
    }

    @Override
    public int createStudent(Student student) {
        return studentDaoOperation.createStudent(student);

    }

    @Override
    public void updatePassword(int userId, String oldPswd, String newPswd) {
        userDaoOperation.updatePassword(userId, oldPswd, newPswd);

    }
}
