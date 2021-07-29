package com.flipkart.business;
import com.flipkart.bean.Student;

/**
 *
 * @author JEDI-Group05
 * Interface for Payment Operations
 */
public interface PaymentInterface {
    /**
     * Method to get Payment Status
     * @param studentId ID of student to get payment status
     * @return Payment status
     */
    boolean getPaymentStatus(int studentId);
    /**
     * Method to make Payment
     * @param student Object storing student details
     * @return fee based on Student details
     */
    double makePayment(Student student);
}