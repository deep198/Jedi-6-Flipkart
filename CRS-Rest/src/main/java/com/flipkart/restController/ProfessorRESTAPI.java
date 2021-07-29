package com.flipkart.restController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.DAO.CourseDaoInterface;
import com.flipkart.DAO.CourseDaoOperation;
import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.bean.Professor;
import com.flipkart.business.*;
import org.hibernate.validator.constraints.Email;
import com.flipkart.bean.Course;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;

import com.flipkart.business.GradeInterface;
import com.flipkart.business.GradeOperation;
@Path("/professor")
public class ProfessorRESTAPI {
    ProfessorInterface professorInterface = new ProfessorOperation();
    CourseInterface courseInterface = new CourseOperation();
    PaymentInterface paymentInterface = new PaymentOperation();
    StudentInterface studentInterface = StudentOperation.getInstance();
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