package com.flipkart.business;

import com.flipkart.bean.Student;

public class PaymentOperation implements PaymentInterface{
    @Override
    public boolean getPaymentStatus(int studentId) {
        return false;
    }

    @Override
    public double makePayment(Student student) {
        return 0;
    }
}
