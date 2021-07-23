package com.flipkart.DAO;

import com.flipkart.bean.User;

public interface UserDaoInterface {

    void createUser(User user);


    User validateUser(int userId, String password);


    void updateUser(User user);
}
