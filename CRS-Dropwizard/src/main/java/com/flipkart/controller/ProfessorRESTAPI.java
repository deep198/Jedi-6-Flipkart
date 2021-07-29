package com.flipkart.controller;

import com.flipkart.DAO.CourseDaoInterface;
import com.flipkart.DAO.CourseDaoOperation;
import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.business.*;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/professor")
public class ProfessorRESTAPI {
    ProfessorInterface professorInterface = new ProfessorOperation();
    CourseInterface courseInterface = new CourseOperation();
    PaymentInterface paymentInterface = new PaymentOperation();
    StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
    CourseDaoInterface courseDaoInterface = new CourseDaoOperation();
    GradeInterface gradeInterface = new GradeOperation();

    /**
     * Handles API request to get selected courses for a professor
     * @param professorId
     * @return
     */
    @GET
    @Path("/getCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(
            @NotNull
            @QueryParam("professorId") int professorId) throws SQLException {
//        Professor professor = professorInterface.fetchProfessor(professorId);
        System.out.println(professorId);
        return Response.status(200).entity(courseDaoInterface.selectedprofcourse(professorId)).build();
    }

    /**
     * Handles API request to upload grades for a professor
     * @param studentId
     * @param courseId
     * @return
     */
    @POST
    @Path("/addGrade/{studentId}/{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @PathParam("studentId") int studentId,
            @NotNull
            @PathParam("courseId") int courseId,
            @QueryParam("marks") int marks) throws ValidationException     {
        System.out.println(studentId + " " + courseId + " " + marks);
        gradeInterface.uploadGrades(studentId, courseId, marks);
        return Response.status(200).entity( "Grade updated for student: "+studentId).build();
    }

    @PUT
    @Path("/addProfCourse/{courseId}/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfCourse(
            @NotNull
            @PathParam("courseId") int courseId,
            @NotNull
            @PathParam("professorId") int professorId) throws SQLException {
        courseInterface.selectprofcourse(courseId,professorId);
        return Response.status(200).entity( "You have successfully added Course : " + courseId).build();
    }


}