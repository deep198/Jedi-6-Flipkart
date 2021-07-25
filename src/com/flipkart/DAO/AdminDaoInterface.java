package com.flipkart.DAO;

import com.flipkart.bean.Admin;

public interface AdminDaoInterface {
    public int createAdmin(Admin admin);

    public void displayAdmins();

    Admin fetchAdmin(int userID);
}