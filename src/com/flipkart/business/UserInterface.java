package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;

public interface UserInterface {

    User validateLogin(int userId, String password)throws InvalidLoginException;

    void createUser(User user);

    int createStudent(Student student);

    void updatePassword(int userId, String oldPswd, String newPswd);
}
