package com.flipkart.DAO;

import com.flipkart.bean.User;

public interface UserDaoInterface {

    public void createUser(User user);


    User validateUser(int userId, String password);
}
