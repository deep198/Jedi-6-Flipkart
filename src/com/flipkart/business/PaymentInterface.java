package com.flipkart.business;

import com.flipkart.bean.Student;

public interface PaymentInterface {
    boolean getPaymentStatus(int studentId);

    double makePayment(Student student);
}
