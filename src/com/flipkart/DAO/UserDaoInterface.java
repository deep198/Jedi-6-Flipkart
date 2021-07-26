package com.flipkart.DAO;

import com.flipkart.bean.User;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;

public interface UserDaoInterface {



    User validateUser(int userId, String password) throws InvalidLoginException, NotApprovedException;

    void updateUser(User user);

    void createUser(User user);

    void updatePassword(int userId, String oldPswd, String newPswd);

}
