package com.flipkart.DAO;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface UserDaoInterface {



    User validateUser(int userId, String password);

    void updateUser(User user);

    void createUser(User user);

    void updatePassword(int userId, String oldPswd, String newPswd);

    void createStudent(Student student);
}
