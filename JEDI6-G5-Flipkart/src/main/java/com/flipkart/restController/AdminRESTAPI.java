package com.flipkart.restController;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Admin;
import com.flipkart.business.*;
import org.hibernate.validator.constraints.Email;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

/**
 *
 * @author JEDI-Group05
 * Admin Rest API Controller
 *
 */
@Path("/admin")
public class AdminRESTAPI {
    AdminInterface adminOperation = AdminOperation.getInstance();
    StudentInterface studentOperation = StudentOperation.getInstance();
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

    @GET
    @Path("/viewProfessors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewProfessors() {
        AdminInterface adminOperation = new AdminOperation();
        List<String> professors = adminOperation.displayProfessors();
        return Response.status(200).entity(professors).build();
    }

    @POST
    @Path("/addCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) {
        CourseInterface courseOperation = new CourseOperation();
        courseOperation.addCourse(course);
        return Response.status(201).entity("Course added successfully!").build();

    }

    @DELETE
    @Path("/deleteCourse/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(
            @PathParam("courseId") int courseId){
        CourseInterface courseOperation = new CourseOperation();
        try {
            courseOperation.deleteCourse(courseId);
            return Response.status(201).entity("Course deleted successfully!").build();
        } catch (CourseNotFoundException e) {
            return Response.status(201).entity("Course with id " + courseId + " not found").build();
        }
    }

}