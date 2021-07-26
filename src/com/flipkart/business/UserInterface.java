package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;

public interface UserInterface {

    User validateLogin(int userId, String password)throws InvalidLoginException, NotApprovedException;

    void createUser(User user);

    int createStudent(Student student);

    void updatePassword(int userId, String oldPswd, String newPswd);
}
