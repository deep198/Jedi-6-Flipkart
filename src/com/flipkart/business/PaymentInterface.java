package com.flipkart.business;

import com.flipkart.bean.Student;

public interface PaymentInterface {
    boolean getPaymentStatus(String studentId);

    double makePayment(Student student);
}
