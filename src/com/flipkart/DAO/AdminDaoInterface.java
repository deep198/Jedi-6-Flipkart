package com.flipkart.DAO;
import com.flipkart.bean.Admin;
/**
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public interface AdminDaoInterface {

    /**
     * Method to create admin using SQL Command
     * @param admin object :which contains the details of the admin
     * @return admin ID
     */
    public int createAdmin(Admin admin);
    /**
     * Method to display list of admins using SQL Command
     */
    public void displayAdmins();
    /**
     * Method to get the details of the admin from particular admin ID
     * @param userID :Similar to admin Id
     * @return Admin Object
     */
    Admin fetchAdmin(int userID);
}