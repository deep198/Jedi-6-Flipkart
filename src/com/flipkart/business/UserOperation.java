package com.flipkart.business;

import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.DAO.UserDaoInterface;
import com.flipkart.DAO.UserDaoOperation;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;
import com.flipkart.exception.UserNotFoundException;

import static com.flipkart.constant.UserRole.STUDENT;

public class UserOperation implements UserInterface{

    UserDaoInterface userDaoOperation = new UserDaoOperation();
    StudentDaoInterface studentDaoOperation = new StudentDaoOperation();


    @Override
    public User validateLogin(int userId, String password) throws InvalidLoginException, NotApprovedException, UserNotFoundException {
        return userDaoOperation.validateUser(userId, password);
    }

    @Override
    public void createUser(User user) {
        userDaoOperation.createUser(user);
    }

    @Override
    public int createStudent(Student student) {

        int studentId = studentDaoOperation.createStudent(student);
        User user = new User();
        user.setUserId(studentId);
        user.setUserName(student.getUserName());
        user.setPassword(student.getPassword());
        user.setUserRole(STUDENT);
        UserDaoInterface userDaoOperation = new UserDaoOperation();
        userDaoOperation.createUser(user);
        return studentId;
    }


    @Override
    public void updatePassword(int userId, String oldPswd, String newPswd) {
        userDaoOperation.updatePassword(userId, oldPswd, newPswd);
    }
}
