package com.flipkart.business;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.IncorrectOldPassword;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * @author JEDI-Group05
 * Interface for User Operations
 *
 */
public interface UserInterface {
    /**
     * Method to validate login for a User
     * @param userId : user id to be validated
     * @param password : password for the particular user
     * @return User Object containing user details
     * @throws InvalidLoginException
     * @throws NotApprovedException
     * @throws UserNotFoundException
     */
    User validateLogin(int userId, String password)throws InvalidLoginException, NotApprovedException, UserNotFoundException;
    /**
     * Method to create new User
     * @param user Object which will contain details
     */
    void createUser(User user);
    /**
     * Method to create Student Profile
     * @param student object which will contain details
     * @return studentId : id of the student
     */
    int createStudent(Student student);
    /**
     * Method to update Password for a user
     * @param userId : Id of the user whose password is being updated
     * @param oldPswd : old password of the user
     * @param newPswd : updated password of the user
     */
    void updatePassword(int userId, String oldPswd, String newPswd) throws IncorrectOldPassword;
}