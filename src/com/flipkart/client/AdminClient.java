package com.flipkart.client;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.business.*;
import com.flipkart.exception.CourseNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class AdminClient {
    // initializing the scanner
    Scanner sc = new Scanner(System.in);
    // admin client landing page
    public void adminClientPage(Admin admin){
        // initializing the instance userOperation to carry out user operations
        UserInterface userOperation= new UserOperation();
        // initializing the instance courseOperation to carry out course operations
        CourseInterface courseOperation = new CourseOperation();
        // initializing the instance AdminOperation to carry out Admin operations
        AdminInterface adminOperation=new AdminOperation();
        //begin variable listing
        User user;
        int userId;
        int userRoleOption;
        Date currentDate = new Date();
        // display contents of admin client landing page
        System.out.println("Welcome "+ admin.getName()+" !");
        boolean login = true;
        while(true) {
            // diplay menu list for admin
            System.out.println("Choose an option");
            System.out.println("1. Create user");
            System.out.println("2. Add a new Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Display Users");
            System.out.println("5. Student Approval");
            System.out.println("6. Logout");
            int choice = sc.nextInt();
            switch(choice) {
                // create a new user
                case 1:
                    System.out.println("Enter User Role ");
                    System.out.println("1. Professor");
                    System.out.println("2. Admin");
                    String userName;
                    userRoleOption = sc.nextInt();

                    switch(userRoleOption){
                        // if user is a professor
                        case 1:
                            Professor professor= new Professor();
                            sc.nextLine();
                            System.out.println("Enter Name: ");
                            String name = sc.nextLine();
                            professor.setName(name);
                            System.out.println("Enter userName: ");
                            userName = sc.nextLine();
                            System.out.println(" : " + userName);
                            professor.setUserName(userName);
                            System.out.println("Enter Password: ");
                            String password = sc.nextLine();
                            System.out.println(" : " + password);
                            professor.setPassword(password);
                            System.out.println("Enter Department");
                            String department = sc.nextLine();
                            System.out.println(" : " + department);
                            professor.setDepartment(department);
                            // create professor
                            userId = adminOperation.createProfessor(professor);
                            System.out.println("Professor created with UserId: " + userId);
                            break;
                        //if user is an admin
                        case 2:
                            Admin newAdmin = new Admin();
                            sc.nextLine();
                            System.out.println("Enter Name: ");
                            name = sc.nextLine();
                            newAdmin.setName(name);
                            System.out.println("Enter UserName: ");
                            userName = sc.nextLine();
                            newAdmin.setUserName(userName);
                            System.out.println("Enter Password: ");
                            password = sc.nextLine();
                            newAdmin.setPassword(password);
                            // create admin
                            userId = adminOperation.createAdmin(newAdmin);
                            System.out.println("Admin is created with userId: " + userId);
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
                                adminOperation.displayStudents();
                                continue;
                                //view professor details
                            case 2:
                                adminOperation.displayProfessors();
                                continue;
                                // view admin details
                            case 3:
                                adminOperation.displayAdmins();
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
                    adminOperation.showunapprovedStudents();
                    List<Integer> approvedStudentsList=new ArrayList<>();
                    System.out.println();
                    System.out.println("Enter the ID of the Student you wish to Approve : ");
                    System.out.println("Enter -1 when you are done");

                    while(true){
                        int studentID=sc.nextInt();
                        if (studentID == -1) break;
                        approvedStudentsList.add(studentID);
                    }
                    System.out.println();
                    adminOperation.approveStudent(approvedStudentsList);
                    System.out.println("Selected Students Successfully Approved ");
                    continue;
                    // log out as admin
                case 6:
                    System.out.println("Succesfully logged out as on "+ currentDate);
                    login = false;
                    break;
            }
            if (! login) break;
        }
    }
}