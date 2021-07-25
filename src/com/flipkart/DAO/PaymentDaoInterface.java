package com.flipkart.DAO;

import com.flipkart.bean.Student;

public interface PaymentDaoInterface {
    void showRegistrationReceipt(Student student);

    int makeRegistrationEntry(Student student, double payableAmount, int paymentMode);
}
