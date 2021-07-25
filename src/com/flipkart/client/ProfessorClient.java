package com.flipkart.client;

import com.flipkart.bean.Professor;
import com.flipkart.business.*;

import java.util.Scanner;

public class ProfessorClient {

    Scanner sc= new Scanner(System.in);

    // professor client landing page
    public void professorClientPage(Professor professor) {

        int courseId;

        CourseInterface courseOperation= new CourseOperation();
        ProfessorInterface professorOperation= new ProfessorOperation();


        // professor client page contents
        System.out.println("Successfully logged in as PROFESSOR  ");
        while(true) {
            System.out.println("Welcome "+professor.getName()+" !");
            // display menu list for professor
            System.out.println("Choose an option");
            System.out.println("1. View Courses to teach");
            System.out.println("2. Select a course");
            System.out.println("3. Deselect a course");
            System.out.println("4. View Selected Courses");
            System.out.println("5. Upload Grades");
            System.out.println("6. Logout");
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
                    System.out.println("Successfully logged out as on ");
                    break;
            }
            break;
        }
    }

}
