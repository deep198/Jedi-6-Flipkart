package com.flipkart.DAO;
import com.flipkart.bean.Student;
/*
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public interface PaymentDaoInterface {
    /**
     * Method to show registration receipt after the payment
     *
     * @param student object :which contains the details of the student
     */
    void showRegistrationReceipt(Student student);

    /**
     * Method to show the amount to be paid by a student of particular semester
     *
     * @param student             object :which contains the details of the student
     * @param payableAmount:Fixed amount for the particular semester
     * @param paymentMode             mode:To select the mode of payment whether card, netbanking or wallet
     * @return Reference id of the payment done.
     */
    int makeRegistrationEntry(Student student, double payableAmount, int paymentMode);
}