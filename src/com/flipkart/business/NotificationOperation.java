package com.flipkart.business;

import com.flipkart.DAO.PaymentDaoInterface;
import com.flipkart.DAO.PaymentDaoOperation;
import com.flipkart.bean.Student;

public class NotificationOperation implements NotificationInterface{

    @Override
    public void showRegistrationReciept(Student student) {
        PaymentDaoInterface paymentDao = new PaymentDaoOperation();
        paymentDao.showRegistrationReceipt(student);
    }
}
