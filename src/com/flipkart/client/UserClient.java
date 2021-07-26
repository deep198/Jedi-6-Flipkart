package com.flipkart.client;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.*;
import com.flipkart.constant.Color;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.util.Scanner;
public class UserClient {

    private static final Logger logger = Logger.getLogger(UserClient.class.getName());

    public static void main(String[] args) {
        //initializing the scanner
        Scanner sc = new Scanner(System.in);
        UserInterface userOperation = new UserOperation();
        StudentInterface studentOperation = new StudentOperation();
        ProfessorInterface professorOperation = new ProfessorOperation();
        AdminInterface adminOperation = new AdminOperation();
        //user login landing page
        while(true) {
            System.out.println(Color.YELLOW_BACKGROUND.toString() + Color.BLACK_BOLD.toString() +"********************WELCOME TO COURSE REGISTRATION SYSTEM********************" + Color.RESET);
            System.out.println("\t\t\t\t\t1. Login  ");
            System.out.println("\t\t\t\t\t2. New Student Registration   ");
            System.out.println("\t\t\t\t\t3. Update Password ");
            System.out.println("\t\t\t\t\t4. Exit ");
            System.out.println(Color.YELLOW_BACKGROUND.toString() + Color.BLACK_BOLD.toString() +"*****************************************************************************" + Color.RESET);
            System.out.println();
            System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+" Choose the option you want :"+ Color.RESET);
            int option=sc.nextInt();
            sc.nextLine();
            switch(option) {
                case 1:
                    // fetching input for user credentials
                    System.out.println("Enter UserId: ");
                    int userId = sc.nextInt();
                    System.out.println("Enter password: ");
                    String password= sc.next();
                    try{
                        // validating the user credentials
                        User checkedUser = userOperation.validateLogin(userId, password);
                        String userRole = checkedUser.getUserRole();
                        userId = checkedUser.getUserId();
                        switch(userRole) {
                            // if user is a student
                            case "STUDENT":
                                // fetching student object from student table
                                Student student = studentOperation.fetchStudent(userId);
                                StudentClient studentClient = new StudentClient();
                                // redirecting to student client landing page
                                studentClient.studentClientPage(student);
                                continue;
                                //if user is a professor
                            case "PROFESSOR":
                                //fetching professor object from professor table
                                Professor professor = professorOperation.fetchProfessor(userId);
                                ProfessorClient professorClient= new ProfessorClient();
                                //redirecting to professor client landing page
                                professorClient.professorClientPage(professor);
                                continue;
                                //if user is an admin
                            case "ADMIN":
                                // fetching admin object from admin table
                                Admin admin = adminOperation.fetchAdmin(userId);
                                AdminClient adminClient= new AdminClient();
                                // redirecting to admin client landing page
                                adminClient.adminClientPage(admin);
                                continue;
                        }
                    }
                    // catching the InvalidLoginException in catch block
                    catch(InvalidLoginException e){
                        logger.error(e.getMessage());
                        continue;
                    } catch (NotApprovedException e) {
                        logger.error(e.getMessage());
                        continue;
                    }
                    catch (UserNotFoundException userNotFoundException) {
                        logger.error(userNotFoundException.getMessage());
                        continue;
                    }
                    break;
                case 2:
                    System.out.println(Color.YELLOW_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"******************************WELCOME NEW STUDENT******************************" + Color.RESET);
                    System.out.println();
                    System.out.println("Enter your details");
                    Student student = new Student();
                    System.out.println("Enter your name : ");
                    String name=sc.nextLine();
                    student.setName(name);
                    System.out.println("Enter your UserName : ");
                    String userName = sc.nextLine();
                    student.setUserName(userName);
                    System.out.println("Enter your Password : ");
                    password = sc.next();
                    student.setPassword(password);
                    System.out.println("Enter your Branch : ");
                    String branch = sc.next();
                    student.setDepartment(branch);
                    System.out.println("Enter your Semester: ");
                    int sem = sc.nextInt();
                    student.setSem(sem);
                    System.out.println();
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+   "\t\t\t\t   Registering New Student to the System ...  \t\t\t\t"+Color.RESET);
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println();
                    int studentId = userOperation.createStudent(student);
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"\t\t\t\t  Your Profile has been successfully created   \t\t\t\t"+Color.RESET);
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"\t\t\t\tYour userId is : " + studentId + " Please use this to login   \t\t\t\t"+Color.RESET);
                    System.out.println("\n\n");
                    try
                    {
                        Thread.sleep(1500);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    break;
                //update password
                case 3:
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"*********************************Update Password******************************"+ Color.RESET);
                    System.out.println();
                    System.out.println("Enter userId: ");
                    userId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Old Password: ");
                    String oldPswd = sc.next();
                    sc.nextLine();
                    System.out.println("Enter New Password");
                    String newPswd = sc.next();
                    sc.nextLine();
                    userOperation.updatePassword(userId, oldPswd, newPswd);
                    System.out.println();
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"*************************Your Password has been reset************************"+ Color.RESET);
                    System.out.println();
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    break;
                case 4:
                    System.out.println(Color.CYAN_BACKGROUND.toString() + Color.BLACK_BOLD.toString()+"**********************Exiting Course Registration System*********************"+ Color.RESET);
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                    break;
            }
            if(option==1 || option==4)
                break;
            else
                continue;
        }
        sc.close();
    }
}