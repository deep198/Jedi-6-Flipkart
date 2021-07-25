package com.flipkart.business;

import com.flipkart.DAO.PaymentDaoInterface;
import com.flipkart.DAO.PaymentDaoOperation;
import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.bean.Student;

public class RegistrationOperation implements RegistrationInterface{

    @Override
    public void displayEnrolledStudentsInCourse(int courseId) {

    }

    @Override
    public void submitRegistration(Student student) {
        StudentDaoInterface studentDao = new StudentDaoOperation();
        //PaymentDaoInterface paymentDao = new PaymentDaoOperation();
        studentDao.setPaymentStatus(student.getStudentId());
    }

    @Override
    public int generateRegistrationReciept(Student student, double payableAmount, int paymentOption) {
        PaymentDaoInterface paymentDao = new PaymentDaoOperation();
        return paymentDao.makeRegistrationEntry(student,payableAmount,paymentOption);
    }
}
