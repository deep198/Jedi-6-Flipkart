package com.flipkart.controller;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-Group05
 * Admin Rest API Controller
 *
 */
@Path("/admin")
public class AdminRESTAPI {
    AdminInterface adminOperation = new AdminOperation();
    StudentInterface studentOperation = new StudentOperation();
    /**
     * /admin/createProfessor
     * REST-service for adding a new professor
     * @param professor
     * @return userId
     */
    @POST
    @Path("/createProfessor")
    @Consumes(MediaType.APPLICATION_JSON)
//********CHECK
    //@Consumes("application/json")
    //@Produces(MediaType.APPLICATION_JSON)
    public Response createProfessor(Professor professor){
        int professorId = 0;
        try {
            professorId = adminOperation.createProfessor(professor);
            System.out.println("Professor added");
            return Response.status(201).entity("Professor with userId: " + professorId + " added").build();
        } catch (Exception ex) {
            System.out.println(professor.toString());
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }
    /**
     * /admin/createAdmin
     * REST-service for adding a new admin
     * @param admin
     * @return userId
     */
    @POST
    @Path("/createAdmin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdmin(Admin admin){
        int adminId = 0;
        try {
            adminId = adminOperation.createAdmin(admin);
            System.out.println("Admin added");
            return Response.status(201).entity("Admin with userId: " + adminId + " added").build();
        } catch (Exception ex) {
            System.out.println(admin.toString());
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }
    }
    /**
     * /admin/viewPendingAdmissions
     * REST-service for getting un approved Students
     * @return
     */
    @GET
    @Path("/showunapprovedStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showunapprovedStudents() {
        List<Integer> unapprovedStudents = adminOperation.showunapprovedStudents();
        if (unapprovedStudents.size() == 0) {
            return Response.status(200).entity("No unapproved students available currently!").build();
        }
        return Response.status(200).entity(unapprovedStudents).build();
    }

    @GET
    @Path("/displayAdmins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayAdmins() {
        List<String> adminDetails =  adminOperation.displayAdmins();
        if (adminDetails.size() == 0) {
            return Response.status(200).entity("Admins list in empty!").build();
        }
        return Response.status(200).entity(adminDetails).build();
    }

    /**
     * Method to handle API request for approving student
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveStudent/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @PathParam("studentId") int studentId
    ) {
        try {
            List<Integer> students = new ArrayList<Integer>();
            students.add(studentId);
            adminOperation.approveStudent(students);
            return Response.status(200).entity("Successfully approved student").build();
        }
        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/viewStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewStudents() {
       AdminInterface adminOperation = new AdminOperation();
       List<String> students = adminOperation.displayStudents();
       return Response.status(200).entity(students).build();


    }
}