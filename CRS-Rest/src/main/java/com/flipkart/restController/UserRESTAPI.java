package com.flipkart.restController;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.User;
import com.flipkart.exception.IncorrectOldPassword;
import com.flipkart.exception.InvalidLoginException;
import com.flipkart.exception.NotApprovedException;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Student;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;


@Path("/user")
public class UserRESTAPI {
    StudentInterface studentInterface=StudentOperation.getInstance();
    UserInterface userInterface =UserOperation.getInstance();


    /**
     *
     * @param userId: email address of the user
     * @param oldPswd : old password stored in db
     * @param newPswd: new password to be stored in db.
     * @return @return 201, if password is updated, else 500 in case of error
     */
    @POST
    @Path("/updatePassword/{userId}/{oldPassword}/{newPassword}")
    public Response updatePassword(
            @NotNull
            //@Email(message = "Invalid User ID: Not in email format")
            @PathParam("userId") int userId,
            @NotNull
            @PathParam("oldPassword") String oldPswd,
            @NotNull
            //@Size(min = 4 , max = 20 , message = "Password length should be between 4 and 20 characters")
            @PathParam("newPassword") String newPswd) throws ValidationException {

//        System.out.println("lol");
        try
        {
            System.out.println(oldPswd);
            System.out.println(newPswd);
            userInterface.updatePassword(userId, oldPswd, newPswd);
            return Response.status(201).entity("Password updated successfully! ").build();
        }
        catch (IncorrectOldPassword e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }


    /**
     *
     * @param userId
     * @param password
     * @return
     */

    @POST
    @Path("/login/{userId}/{password}")
    public Response validateLogin(
            @NotNull
            @PathParam("userId") int userId,
            @NotNull
            @PathParam("password") String password) throws ValidationException {

        try
        {
            System.out.println(userId+" "+ password);
            User checkedUser = userInterface.validateLogin(userId, password);
                return Response.status(200).entity("Login Successful").build();
        }
        catch (UserNotFoundException e)
        {
            return Response.status(500).entity(e.getMessage()).build();
        } catch (InvalidLoginException e) {
            return Response.status(500).entity(e.getMessage()).build();
        } catch (NotApprovedException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    @POST
    @Path("/studentRegistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) {
        int studentId = 0;
        try
        {
            studentId = userInterface.createStudent(student);
            System.out.println("Student added");
        }
        catch(Exception ex)
        {
            System.out.println(student.toString());
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
        return Response.status(201).entity("Registration Successful for student, your student Id is"+studentId).build();
    }



}
