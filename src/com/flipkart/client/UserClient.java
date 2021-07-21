package com.flipkart.client;

import java.util.Scanner;

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
        while(true) {
            System.out.println("*******WELCOME TO COURSE REGISTRATION SYSTEM***********");
            System.out.println("Enter your credentials");

            // fetching input for user credentials
            System.out.println("Enter UserName: ");
            String username= sc.next();
            System.out.println("Enter password: ");
            String password= sc.next();
            try{

                // validating the user credentials
                User checkedUser= userOperation.validateUser(username, password);
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
                        Professor professor = userOperation.fetchProfessor(userName);
                        ProfessorClient professorClient= new ProfessorClient();
                        //redirecting to professor client landing page
                        professorClient.professorClientPage(professor);
                        continue;

                        //if user is an admin
                    case "ADMIN":
                        // fetching admin object from admin table
                        Admin admin = userOperation.fetchAdmin(userName);
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
        }
    }


}