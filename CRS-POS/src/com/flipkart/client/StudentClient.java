package com.flipkart.client;

import com.flipkart.DAO.RegisteredCourseDaoInterface;
import com.flipkart.DAO.RegisteredCourseDaoOperation;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.constant.Color;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Scanner;


public class StudentClient {

    private static final Logger logger = Logger.getLogger(StudentClient.class.getName());
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
        logger.info("Successfully logged in as STUDENT");
        System.out.println("Welcome "+student.getName()+"!");
        while(true) {
            System.out.print(Color.BLACK_BOLD);
            System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
//            System.out.println("*********************************************************************");
            // Menu for a Student
            System.out.println("\t\t\t\t\t1. View Available Courses");
            System.out.println("\t\t\t\t\t2. Register for a course");
            System.out.println("\t\t\t\t\t3. Drop a course");
            System.out.println("\t\t\t\t\t4. View Registered Courses: ");
            System.out.println("\t\t\t\t\t5. Make semester registration payment");
            System.out.println("\t\t\t\t\t6. View Grades");
            System.out.println("\t\t\t\t\t7. Logout");
            System.out.print(Color.BLACK_BOLD);
            System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
            System.out.println("");
            System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+" Choose the option you want :"+ Color.RESET);
            option= sc.nextInt();

            switch(option) {
                // View all available courses for a particular student
                case 1:
                    courseOperation.viewCourses(student);
                    continue;

                    // add a course by student
                case 2:
                    if(!paymentOperation.getPaymentStatus(student.getStudentId())) {
                        System.out.println("Enter Course ID of the Course to be registered");
                        courseId= sc.nextInt();
                        studentOperation.registerForCourse(courseId, student);
                        System.out.println("Successfully registered for Course " + courseId + "!!");
                    }
                    else {
                        System.out.println("Course registration already complete!");
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
                        System.out.println("Course registration already complete!");
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
                        System.out.println("Registration already complete!");
                        System.out.print(Color.BLACK_BOLD);
                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
                        System.out.println("\t\t\t\t\t1. Show Registration Receipt");
                        System.out.println("\t\t\t\t\t2. Back to main menu");
                        System.out.print(Color.BLACK_BOLD);
                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
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
                        System.out.print(Color.BLACK_BOLD);
                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
                        System.out.println("\t\t\t\t\t1. Submit Registration");
                        System.out.println("\t\t\t\t\t2. Back to main menu");
                        System.out.print(Color.BLACK_BOLD);
                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
                        option= sc.nextInt();
                        switch(option) {

                            // continuing with registration
                            // calculating and displaying payment amount
                            case 1:
                                double payableAmount= paymentOperation.makePayment(student);
                                System.out.print(Color.BLACK_BOLD);
                                System.out.println(Color.YELLOW_BACKGROUND +"************************PAYMENT DETAILS******************************" + Color.RESET);
//                            System.out.println("************************PAYMENT DETAILS******************************");

                                System.out.println("Total payment amount: " + payableAmount);
                                System.out.println("");
                                System.out.println("");

                                // Further Options
                                System.out.print(Color.BLACK_BOLD);
                                System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
                                System.out.println("\t\t\t\t\t1. Pay Fees");
                                System.out.println("\t\t\t\t\t2. Back to main menu");
                                System.out.print(Color.BLACK_BOLD);
                                System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);

                                int option2= sc.nextInt();
                                switch(option2) {

                                    //Mode Of Payment
                                    case 1:
                                        System.out.print(Color.BLACK_BOLD);
                                        System.out.println(Color.YELLOW_BACKGROUND +"************************PAYMENT GATEWAY******************************" + Color.RESET);
//                                    System.out.println("************************PAYMENT GATEWAY******************************");
                                        System.out.println("\t\t\t\t\tChoose the mode of payment");
                                        System.out.println("\t\t\t\t\t1. Card");
                                        System.out.println("\t\t\t\t\t2. E-Wallet");
                                        System.out.println("\t\t\t\t\t3. Netbanking");
                                        System.out.print(Color.BLACK_BOLD);
                                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
                                        int paymentOption= sc.nextInt();

                                        // submitting registration
                                        registrationOperation.submitRegistration (student);
                                        Date currentDate = new Date();
                                        switch(paymentOption) {
                                            // fees submission using debit card
                                            case 1:
                                                System.out.println("Registration successfully completed on "+currentDate+" using CARD");
                                                break;
                                            // fees submission using e-wallet
                                            case 2:
                                                System.out.println("Registration successfully completed on "+currentDate+" using E-WALLET");
                                                break;
                                            // fees submission using netbanking
                                            case 3:
                                                System.out.println("Registration successfully completed on "+currentDate+" using NETBANKING");
                                                break;

                                        }

                                        // registration details stored in database
                                        registrationOperation.generateRegistrationReciept(student, payableAmount, paymentOption);
                                        // listing further actions
                                        System.out.print(Color.BLACK_BOLD);
                                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
                                        System.out.println("\t\t\t\t\t1. Show Registration Receipt");
                                        System.out.println("\t\t\t\t\t2. Back to main menu");
                                        System.out.print(Color.BLACK_BOLD);
                                        System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
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
                    System.out.println("Logged out successfully!");
                    break;
            }
            break;
        }
    }
}