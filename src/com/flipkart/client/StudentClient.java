package com.flipkart.client;

import com.flipkart.DAO.RegisteredCourseDaoInterface;
import com.flipkart.DAO.RegisteredCourseDaoOperation;
import com.flipkart.bean.Student;
import com.flipkart.business.*;

import java.util.Date;
import java.util.Scanner;


public class StudentClient {


    Scanner sc= new Scanner(System.in);

    // studentClient page
    public void studentClientPage(Student student) {

        // initializing the instance courseOperation to carry out course operations
        CourseInterface courseOperation= new CourseOperation();
        // initializing the instance studentOperation to carry out student operations
        StudentInterface studentOperation= new StudentOperation();

        PaymentInterface paymentOperation = new PaymentOperation();

        // begin variable listing
        int option;
        int courseId;


        //student client landing page contents
        System.out.println("Succesfully logged in as STUDENT  ");
        while(true) {
            System.out.println("Welcome "+student.getName()+" !");

            // Menu for a Student
            System.out.println("Choose an option");
            System.out.println("1. View All Courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. View Selected Courses");
            System.out.println("5. Make semester registration payment");
            System.out.println("6. View Grades");
            System.out.println("7. Logout");
            option= sc.nextInt();

            switch(option) {
                // View all available courses for a particular student
                case 1:
                    courseOperation.viewCourses(student);
                    continue;

                    // add a course by student
                case 2:
                    if(!paymentOperation.getPaymentStatus(student.getStudentId())) {
                        System.out.println("Enter Course ID to be registered");
                        courseId= sc.nextInt();
                        studentOperation.registerForCourse(courseId, student);
                        System.out.println("Successfully registered for Course " + courseId + " !!");
                    }
                    else {
                        System.out.println("Registration completed!");
                    }
                    continue;

                    // Drop a course
                case 3:
                    if(!paymentOperation.getPaymentStatus(student.getStudentId())) {
                        System.out.println("Enter CourseID of the Course to be dropped");
                        courseId= sc.nextInt();
                        StudentOperation.dropCourse(courseId, student);
                    }
                    else {
                        System.out.println("Registration completed!");
                    }
                    continue;

                    // View all the courses
                case 4:
                    studentOperation.viewCourses(student);
                    continue;

                    // Submit registration
                case 5:
                    RegistrationInterface registrationOperation = new RegistrationOperation();
                    // if student has already completed registration
                    if(paymentOperation.getPaymentStatus(student.getStudentId())) {
                        System.out.println("Registration already completed !");
                        System.out.println("1. Show Registration Reciept");
                        System.out.println("2. Back to main menu");
                        option = sc.nextInt();
                        switch (option) {

                            //  Course registration receipt
                            case 1:
                                NotificationInterface notificationOperation = new NotificationOperation();
                                notificationOperation.showRegistrationReciept(student);
                                break;

                            // back to main menu
                            case 2:
                                break;
                        }
                    }

                    // if number of courses selected by students is <4 or >4

                    RegisteredCourseDaoInterface registeredCourseDao= new RegisteredCourseDaoOperation();
                    int numberOfSelectedCourses = registeredCourseDao.displayGradeCard(student).size();
                    if(numberOfSelectedCourses<4||numberOfSelectedCourses>4) {
                        System.out.println("Please select EXACTLY 4 courses !");
                        System.out.println("Currently you have selected " + numberOfSelectedCourses + " courses!!");

                    }

                // if number of courses selected is exactly 4 and  courses are available
                else {


                    // Further Options
                    System.out.println("1. Submit Registration");
                    System.out.println("2. Back to main menu");
                    option= sc.nextInt();
                    switch(option) {

                        // continuing with registration
                        // calculating and displaying payment amount
                        case 1:
                            double payableAmount= paymentOperation.makePayment(student);
                            System.out.println("************************PAYMENT DETAILS******************************");

                            System.out.println("Total payment amount: " + payableAmount);


                            System.out.println("*********************************************************************");


                            // Further Options

                            System.out.println("1. Pay Fees");
                            System.out.println("2. Back to main menu");

                            int option2= sc.nextInt();
                            switch(option2) {

                                //Mode Of Payment
                                case 1:
                                    System.out.println("************************PAYMENT GATEWAY******************************");
                                    System.out.println("Choose mode of payment");
                                    System.out.println("1. Debit Card");
                                    System.out.println("2. E- Wallet");
                                    System.out.println("3. Netbanking");
                                    System.out.println("*********************************************************************");
                                    int paymentOption= sc.nextInt();

                                    // submitting registration
                                    registrationOperation.submitRegistration (student);
                                    Date currentDate = new Date();
                                    switch(paymentOption) {
                                        // fees submission using debit card
                                        case 1:
                                            System.out.println("Registration succesfully completed on "+currentDate+" using DEBIT_CARD");
                                            break;
                                        // fees submission using e-wallet
                                        case 2:
                                            System.out.println("Registration succesfully completed on "+currentDate+" using E_WALLET");
                                            break;
                                        // fees submission using netbanking
                                        case 3:
                                            System.out.println("Registration succesfully completed on "+currentDate+" using NETBANKING");
                                            break;

                                    }

                                    // registration details stored in database
                                    registrationOperation.generateRegistrationReciept(student, payableAmount, paymentOption);
                                    // listing further actions
                                    System.out.println("1. Show Registration Reciept");
                                    System.out.println("2. Back to main menu");
                                    int option3= sc.nextInt();
                                    boolean goToMainMenu = false;
                                    switch(option3){
                                        // fetching and displaying registration
                                        case 1:
                                            NotificationInterface notificationOperation= new NotificationOperation();
                                            notificationOperation.showRegistrationReciept(student);
                                            break;

                                        case 2:
                                            goToMainMenu = true;
                                            break;
                                    }

                                    break;
                                case 2:
                                    break;
                            }
                            break;

                        case 2:
                            break;


                    }
                }
                    continue;

                    // display grades for student
                case 6:
                    studentOperation= new StudentOperation();
                    studentOperation.viewGrades(student);
                    continue;

                    // log out as a student
                case 7:
                    System.out.println("Succesfully logged out as on ");
                    break;
            }
            break;
        }
    }
}


