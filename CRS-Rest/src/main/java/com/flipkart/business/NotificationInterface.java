package com.flipkart.business;
import com.flipkart.bean.Student;
/**
 *
 * @author JEDI-Group05
 * Interface for Notification Operations
 */


public interface NotificationInterface {
    /**
     * Method to display registration receipt
     * @param student Object storing details of a student
     */
    void showRegistrationReciept(Student student);
}