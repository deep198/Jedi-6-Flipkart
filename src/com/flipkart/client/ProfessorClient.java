package com.flipkart.client;

import com.flipkart.bean.Professor;
import com.flipkart.business.*;
import com.flipkart.constant.Color;

import java.util.Scanner;

public class ProfessorClient {

    Scanner sc= new Scanner(System.in);

    // professor client landing page
    public void professorClientPage(Professor professor) {

        int courseId;

        CourseInterface courseOperation= new CourseOperation();
        ProfessorInterface professorOperation= new ProfessorOperation();


        // professor client page contents
        System.out.println("\t\t\t"+Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"Successfully logged in as PROFESSOR"+ Color.RESET);
        while(true) {
            System.out.println("\t\t\t\t\t"+Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"Welcome "+professor.getName()+" !"+ Color.RESET);
            // display menu list for professor
            System.out.print(Color.BLACK_BOLD);
            System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
            System.out.println("\t\t\t\t\t1. View Courses to teach");
            System.out.println("\t\t\t\t\t2. Select a course");
            System.out.println("\t\t\t\t\t3. Deselect a course");
            System.out.println("\t\t\t\t\t4. View Selected Courses");
            System.out.println("\t\t\t\t\t5. Upload Grades");
            System.out.println("\t\t\t\t\t6. Logout");
            System.out.print(Color.BLACK_BOLD);
            System.out.println(Color.YELLOW_BACKGROUND +"*********************************************************************" + Color.RESET);
            System.out.println("");
            System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+" Choose the option you want :"+ Color.RESET);
            int choice= sc.nextInt();
            switch(choice) {
                // display all the courses available for professor to teach
                case 1:
                    courseOperation.displayCoursesProfessor();
                    continue;
                    // select a course to teach
                case 2:
                    System.out.println("Enter CourseID of the Course to be selected");
                    courseId= sc.nextInt();
                    professorOperation.selectCourse(courseId, professor);
                    continue;
                    //deselect a course
                case 3:
                    System.out.println("Enter CourseID of the course to be deselected");
                    courseId= sc.nextInt();
                    professorOperation.deleteProfessorCourse(courseId,professor);
                    continue;
                    // display list of courses selected by professor to teach
                case 4:
                    professorOperation.displaySelectedCoursesProfessor(professor);
                    continue;
                    // upload grades
                case 5:
                    RegistrationInterface registrationOperation= new RegistrationOperation();
                    System.out.println("Enter the CourseID to upload grades");
                    courseId= sc.nextInt();
                    // shows the list of students enrolled in particular course
                    registrationOperation.displayEnrolledStudentsInCourse(courseId);
                    while(true)
                    {
                        System.out.println("Enter the StudentID to upload grades or enter -1 to exit");
                        int studentId= sc.nextInt();
                        if(studentId==-1)
                            break;
                        System.out.println("Enter the marks");
                        int marks= sc.nextInt();
                        // grade submission
                        GradeInterface gradeOperation= new GradeOperation();
                        gradeOperation.uploadGrades(studentId, courseId, marks);
                    }
                    continue;
                    // log out as a professor
                case 6:
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+" Logged out successfully "+ Color.RESET);
                    System.out.println("");
                    break;
            }
            break;
        }
    }

}