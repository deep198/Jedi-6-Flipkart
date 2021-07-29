package com.flipkart.bean;

public class Payment {
    private int studentId;
    private boolean paymentStatus;
    private float amount;

    public boolean getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

}
