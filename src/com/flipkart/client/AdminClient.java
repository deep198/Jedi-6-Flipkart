package com.flipkart.client;

import java.util.Date;

import java.util.Scanner;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.CourseInterface;
import com.flipkart.business.CourseOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;

import static com.flipkart.constant.UserRole.*;


public class AdminClient {

    // initializing the scanner
    Scanner sc = new Scanner(System.in);

    // admin client landing page
    public void adminClientPage(Admin admin){

        // initializing the instance userOperation to carry out user operations
        UserInterface userOperation= new UserOperation();
        // initializing the instance courseOperation to carry out course operations
        CourseInterface courseOperation = new CourseOperation();

        //begin variable listing
        User user;
        int userId;
        int userRoleOption;

        Date currentDate = new Date();
        // display contents of admin client landing page
        System.out.println("Succesfully logged in as ADMIN on "+ currentDate);
        boolean login = true;
        while(true) {
            // diplay menu list for admin
            System.out.println("Welcome "+ admin.getName()+" !");
            System.out.println("Choose an option");
            System.out.println("1. Create user");
            System.out.println("2. Add a new Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Display users");
            System.out.println("5. Display Professors");
            System.out.println("6. Display Students");
            System.out.println("7. Logout");

            int choice = sc.nextInt();
            switch(choice) {
                // create a new user
                case 1:
                    user = new User();

                    System.out.println("Enter UserName: ");
                    String userName = sc.next();
                    user.setUserName(userName);
                    System.out.println("Enter Password: ");
                    user.setPassword(sc.next());

                    System.out.println("Enter User Role ");
                    System.out.println("1. Student");
                    System.out.println("2. Professor");
                    System.out.println("3. Admin");
                    userRoleOption = sc.nextInt();
                    switch(userRoleOption){
                        //if user is a student
                        case 1:
                            user.setUserRole(STUDENT);
                            userOperation.createUser(user);
                            Student student = new Student();
                            student.setUserName(userName);
                            System.out.println("Enter Name");
                            student.setName(sc.next());
                            System.out.println("Enter Semester");
                            student.setSem(sc.nextInt());
                            System.out.println("Enter Department");
                            student.setDepartment(sc.next());
                            student.setPaymentStatus(false);
                            // create student
                            userOperation.createStudent(student);
                            break;

                        // if user is a professor
                        case 2:
                            user.setUserRole(PROFESSOR);
                            userOperation.createUser(user);
                            Professor professor= new Professor();
                            professor.setUserName(userName);
                            System.out.println("Enter Name");
                            professor.setName(sc.next());
                            System.out.println("Enter Department");
                            professor.setDepartment(sc.next());
                            // create professor
                            userOperation.createProfessor(professor);
                            break;

                        //if user is an admin
                        case 3:
                            user.setUserRole(ADMIN);
                            userOperation.createUser(user);
                            Admin newAdmin= new Admin();
                            newAdmin.setUserName(userName);
                            System.out.println("Enter Name");
                            newAdmin.setName(sc.next());
                            // create admin
                            userOperation.createAdmin(newAdmin);
                            break;
                    }
                    continue;

                    // update an existing user


                    // insert a new course
                case 2:
                    Course course= new Course();
                    System.out.println("Enter CousreId");
                    course.setCourseId(sc.nextInt());
                    System.out.println("Enter Course Name");
                    course.setCourseName(sc.next());
                    System.out.println("Enter Department ");
                    course.setDepartment(sc.next());
                    // course added in the database
                    courseOperation.addCourse(course);
                    continue;

                    // delete an existing course
                case 3:
                    System.out.println("Enter CourseId of course to be deleted");
                    int courseId= sc.nextInt();
                    // course deleted from database
                    try {
                        courseOperation.deleteCourse(courseId);
                    } catch (CourseNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    continue;

                    // view user details
                case 4:
                    while(true) {
                        System.out.println("1. View Student Details");
                        System.out.println("2. View Professor Details");
                        System.out.println("3. View Admin Details");
                        System.out.println("4. Go Back to Main Menu");
                        int display= sc.nextInt();
                        switch(display) {
                            // view studennt details
                            case 1:
                                userOperation.displayStudents();
                                continue;

                                //view professor details
                            case 2:
                                userOperation.displayProfessors();
                                continue;

                                // view admin details
                            case 3:
                                userOperation.displayAdmins();
                                continue;

                                // back to main menu
                            case 4:
                                break;
                        }

                        break;
                    }
                    continue;

                //Display Professors
                case 5:
                    userOperation.displayProfessors();
                    break;
                //Display Students
                case 6:
                    userOperation.displayStudents();
                    break;

                    // log out as admin
                case 7:
                    System.out.println("Succesfully logged out as on "+ currentDate);
                    login = false;
                    break;
            }
            if (! login) break;
        }

    }

}