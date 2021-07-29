package com.flipkart.business;
import com.flipkart.DAO.PaymentDaoInterface;
import com.flipkart.DAO.PaymentDaoOperation;
import com.flipkart.bean.Student;
/**
 *
 * @author JEDI-Group05
 * Implementations of Notification Operations
 */
public class NotificationOperation implements NotificationInterface{
    /**
     * Method to display registration receipt
     * @param student Object storing details of a student
     */
    @Override
    public void showRegistrationReciept(Student student) {
        PaymentDaoInterface paymentDao = new PaymentDaoOperation();
        paymentDao.showRegistrationReceipt(student);
    }
}