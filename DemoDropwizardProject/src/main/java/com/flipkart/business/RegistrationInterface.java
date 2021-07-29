package com.flipkart.business;
import com.flipkart.bean.Student;

/**
 *
 * @author JEDI-Group05
 * Interface for Registration Operations
 *
 */
public interface RegistrationInterface {
    /**
     * Method to submit Registration for a student and set Payment status
     * @param student Object who is submitting for registration
     */
    void submitRegistration(Student student);
    /**
     * Method to generate payment receipt
     * @param student Object containing details for whom receipt is generated
     * @param payableAmount : amount to be payed for the particular semester
     * @param paymentOption : the payment option selected byb student
     * @return payment registration
     */
    int generateRegistrationReciept(Student student, double payableAmount, int paymentOption);
    void displayEnrolledStudentsInCourse(int courseId);
}