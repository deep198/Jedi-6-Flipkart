package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.*;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;

import java.util.Scanner;

public class UserClient {

    public static void main(String[] args) {

        //initializing the scanner
        Scanner sc = new Scanner(System.in);

        UserInterface userOperation = new UserOperation();
        StudentInterface studentOperation = new StudentOperation();
        ProfessorInterface professorOperation = new ProfessorOperation();
        AdminInterface adminOperation = new AdminOperation();


        //user login landing page

        while(true) {
            System.out.println("**WELCOME TO COURSE REGISTRATION SYSTEM****");
            System.out.println("1. Login  ");
            System.out.println("2. New Student Registration   ");
            System.out.println("3. Update Password ");

            System.out.println();

            System.out.println(" Choose the option you want ...");

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
                        String userName = checkedUser.getUserName();
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
                        System.out.println(e.getMessage());
                        continue;
                    } catch (NotApprovedException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    break;

                case 2:
                    System.out.println("****WELCOME NEW STUDENT****");
                    System.out.println("***Enter your details***");
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
                    System.out.println("Enter your sem: ");
                    int sem = sc.nextInt();
                    student.setSem(sem);
                    System.out.println();
                    System.out.println(" Registering New Student to the System ...");
                    System.out.println();
                    int studentId = userOperation.createStudent(student);
                    System.out.println("**Your Profile has been successfully created****");
                    System.out.println("Your userId is : " + studentId + " Please use this to login");
                    break;

                //update password
                case 3:
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
            }

            if(option==1)
                break;
            else
                continue;
        }
        sc.close();
    }


}