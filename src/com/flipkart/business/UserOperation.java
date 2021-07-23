package com.flipkart.business;

import com.flipkart.DAO.UserDaoInterface;
import com.flipkart.DAO.UserDaoOperation;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;

public class UserOperation implements UserInterface{

    UserDaoInterface userDaoOperation = new UserDaoOperation();

    @Override
    public User validateLogin(int userId, String password) throws InvalidLoginException {
        return userDaoOperation.validateUser(userId, password);
    }

    @Override
    public void createUser(User user) {
        userDaoOperation.createUser(user);
    }

    @Override
    public void createStudent(Student student) {
        userDaoOperation.createStudent(student);
    }

    @Override
    public void updatePassword(int userId, String oldPswd, String newPswd) {
        userDaoOperation.updatePassword(userId, oldPswd, newPswd);

    }
}
