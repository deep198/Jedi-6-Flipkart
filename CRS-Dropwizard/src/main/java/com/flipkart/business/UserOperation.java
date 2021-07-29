package com.flipkart.business;

import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.DAO.UserDaoInterface;
import com.flipkart.DAO.UserDaoOperation;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.IncorrectOldPassword;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;
import com.flipkart.exception.UserNotFoundException;

import static com.flipkart.constant.UserRole.STUDENT;

/**
 *
 * @author JEDI-Group05
 * Implementations of User Operations
 *
 */
public class UserOperation implements UserInterface{
    UserDaoInterface userDaoOperation = new UserDaoOperation();
    StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
    /**
     * Method to validate login for a User
     * @param userId : user id to be validated
     * @param password : password for the particular user
     * @return User Object containing user details
     * @throws InvalidLoginException
     * @throws NotApprovedException
     * @throws UserNotFoundException
     */
    @Override
    public User validateLogin(int userId, String password) throws InvalidLoginException, NotApprovedException, UserNotFoundException {
        return userDaoOperation.validateUser(userId, password);
    }
    /**
     * Method to create new User
     * @param user Object which will contain details
     */
    @Override
    public void createUser(User user) {
        userDaoOperation.createUser(user);
    }
    /**
     * Method to create Student Profile
     * @param student object which will contain details
     * @return studentId : id of the student
     */
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
    /**
     * Method to update Password for a user
     * @param userId : Id of the user whose password is being updated
     * @param oldPswd : old password of the user
     * @param newPswd : updated password of the user
     */
    @Override
    public void updatePassword(int userId, String oldPswd, String newPswd) throws IncorrectOldPassword {
        userDaoOperation.updatePassword(userId, oldPswd, newPswd);
    }
}