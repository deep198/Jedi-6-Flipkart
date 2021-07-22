package com.flipkart.client;

import java.util.Scanner;


import com.flipkart.DAO.TemporaryDataStore;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;


public class UserClient {

    public static void main(String[] args) {

        //initializing the scanner
        Scanner sc = new Scanner(System.in);

        // initializing the instance courseOperation to carry out user operations
        UserInterface userOperation = new UserOperation();

        //user login landing page
        //Adding dummy objects for testing
        TemporaryDataStore temporaryDataStore = new TemporaryDataStore();

        Professor professor = new Professor();
        professor.setUserName("prof");
        professor.setProfessorId(201);
        professor.setDepartment("EEE");
        professor.setPassword("pass");
        professor.setName("Random Prof");
        temporaryDataStore.addProfessor(professor);

        Admin admin = new Admin();

        admin.setUserName("admin");
        admin.setName("Random Admin");
        admin.setPassword("pass");
        admin.setAdminId(101);

        temporaryDataStore.addAdmin(admin);
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
                    System.out.println("Enter UserName: ");
                    String username= sc.next();
                    System.out.println("Enter password: ");
                    String password= sc.next();
                    try{

                        // validating the user credentials
                        User checkedUser= userOperation.validateLogin(username, password);
                        String userRole = checkedUser.getUserRole();
                        String userName = checkedUser.getUserName();

                        switch(userRole) {
                            // if user is a student
                            case "STUDENT":
                                // fetching student object from student table
                                Student student = userOperation.fetchStudent(userName);
                                StudentClient studentClient = new StudentClient();
                                // redirecting to student client landing page
                                studentClient.studentClientPage(student);
                                continue;

                                //if user is a professor
                            case "PROFESSOR":
                                //fetching professor object from professor table
                                professor = userOperation.fetchProfessor(userName);
                                ProfessorClient professorClient= new ProfessorClient();
                                //redirecting to professor client landing page
                                professorClient.professorClientPage(professor);
                                continue;

                                //if user is an admin
                            case "ADMIN":
                                // fetching admin object from admin table
                                admin = userOperation.fetchAdmin(userName);
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
                    }

                    break;

                case 2:
                    System.out.println("**WELCOME NEW STUDENT****");
                    System.out.println("Enter your details : ");
                    System.out.println();
                    Student student = new Student();
                    System.out.println(" Enter your Roll No. : ");
                    String roll=sc.next();
                    student.setStudentId(roll);
                    sc.nextLine();
                    System.out.println("Enter your Username : ");
                    username = sc.next();
                    student.setUserName(username);
                    sc.nextLine();
                    System.out.println("Enter your Password : ");
                    password = sc.next();
                    student.setPassword(password);
                    sc.nextLine();
                    System.out.println("Enter your name : ");
                    String name=sc.nextLine();
                    student.setName(name);
                    System.out.println("Enter your Branch : ");
                    String branch = sc.next();
                    student.setDepartment(branch);
                    sc.nextLine();

                    System.out.println();

                    System.out.println(" Registering New Student to the System ...");

                    System.out.println();
                    userOperation.createStudent(student);

                    System.out.println("**New Student Created****");

                    System.out.println(roll+" "+name+" "+branch+" " +"\n");

                    break;

                //update password
                case 3:
                    System.out.println("Enter Username: ");
                    username = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Old Password: ");
                    String oldPswd = sc.next();
                    sc.nextLine();
                    System.out.println("Enter New Password");
                    String newPswd = sc.next();
                    sc.nextLine();

                    userOperation.updatePassword(username, oldPswd, newPswd);
            }

            if(option==1)
                break;
            else
                continue;
        }

        sc.close();
    }


}