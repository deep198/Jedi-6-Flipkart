package com.flipkart.business;

import com.flipkart.bean.Student;

public interface RegistrationInterface {
    void submitRegistration(Student student);

    int generateRegistrationReciept(Student student, double payableAmount, int paymentOption);

    void displayEnrolledStudentsInCourse(int courseId);
}
