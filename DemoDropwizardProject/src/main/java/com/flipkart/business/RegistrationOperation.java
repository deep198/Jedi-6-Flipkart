package com.flipkart.business;
import com.flipkart.DAO.PaymentDaoInterface;
import com.flipkart.DAO.PaymentDaoOperation;
import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.bean.Student;

/**
 *
 * @author JEDI-Group05
 * Implementations of Registration Operations
 *
 */
public class RegistrationOperation implements RegistrationInterface{
    @Override
    public void displayEnrolledStudentsInCourse(int courseId) {
    }
    /**
     * Method to submit Registration for a student and set Payment status
     * @param student Object who is submitting for registration
     */
    @Override
    public void submitRegistration(Student student) {
        StudentDaoInterface studentDao = new StudentDaoOperation();
        //PaymentDaoInterface paymentDao = new PaymentDaoOperation();
        studentDao.setPaymentStatus(student.getStudentId());
    }
    /**
     * Method to generate payment receipt
     * @param student Object containing details for whom receipt is generated
     * @param payableAmount : amount to be payed for the particular semester
     * @param paymentOption : the payment option selected byb student
     * @return payment registration
     */
    @Override
    public int generateRegistrationReciept(Student student, double payableAmount, int paymentOption) {
        PaymentDaoInterface paymentDao = new PaymentDaoOperation();
        return paymentDao.makeRegistrationEntry(student,payableAmount,paymentOption);
    }
}