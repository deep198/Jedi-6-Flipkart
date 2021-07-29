package com.flipkart.restController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.DAO.*;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.constant.PaymentMode;
import com.sun.istack.internal.NotNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;
/**
 * @author JEDI - 05
 *
 */
@Path("/student")
public class StudentRESTAPI {
     RegistrationInterface registrationInterface = new RegistrationOperation();
    ProfessorInterface professorInterface = new ProfessorOperation();
    CourseInterface courseInterface = new CourseOperation();
    PaymentInterface paymentInterface = new PaymentOperation();
    StudentInterface studentInterface = StudentOperation.getInstance();
    StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
    RegisteredCourseDaoInterface registeredCourseDaoInterface = new RegisteredCourseDaoOperation();
    CourseDaoInterface courseDaoInterface = new CourseDaoOperation();
    ObjectMapper mapper = new ObjectMapper();
//    private static Logger logger = Logger.getLogger(StudentRestAPI.class);
    /**
     * Method to handle API request for course registration
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param studentId
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/registerCourses/{studentId}/{c1}/{c2}/{c3}/{c4}/{sem}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourses(
            @NotNull
            @PathParam("studentId") int studentId,
            @PathParam("c1") int c1,
            @PathParam("c2") int c2,
            @PathParam("c3") int c3,
            @PathParam("c4") int c4,
            @NotNull
            @PathParam("sem") int sem) throws SQLException{
        List<Integer> courseList = new ArrayList<Integer>();
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        Set<Integer> hash_set = new HashSet<Integer>();
        for(int courseId:courseList) {
            if(!hash_set.add(courseId))
                return Response.status(500).entity("Duplicate value : "+courseId).build();
            registeredCourseDaoInterface.registerCourseForStudent(studentId, courseId, sem);
        }
        return Response.status(201).entity( "Registration Successful").build();
    }
    /**
     * Handles api request to add a course
     * @param courseId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @PUT
    @Path("/addCourse/{courseId}/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(
            @NotNull
            @PathParam("courseId") int courseId,
            @NotNull
            @PathParam("studentId") int studentId) throws SQLException{

        Student student = studentInterface.fetchStudent(studentId);
        studentInterface.registerForCourse(courseId,student);
        return Response.status(201).entity( "You have successfully added Course : " + courseId).build();
    }
    /**
     * Handles API request to drop a course
     * @param courseId
     * @param studentId
     * @return
     * @throws SQLException
     */
    @DELETE
    @Path("/dropCourse/{courseCode}/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(
            @NotNull
            @PathParam("courseCode") int courseId,
            @NotNull
            @PathParam("studentId") int studentId) throws SQLException{
        StudentOperation studentOperation = new StudentOperation();
        Student student = studentInterface.fetchStudent(studentId);
        studentOperation.dropCourse(courseId,student);
        return Response.status(201).entity( "You have successfully dropped Course : " + courseId).build();
    }
    /**
     * Method handles API request to view the list of available courses for a student
     * @param studentId
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/viewAvailableCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourse(
            @NotNull
            @QueryParam("studentId") int studentId) throws SQLException{
        Student student = studentInterface.fetchStudent(studentId);
        return courseDaoInterface.displayCourses(student);
    }
    /**
     * Method handles API request to view the list of registered courses for a student
     * @param studentId
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/viewRegisteredCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewRegisteredCourse(
            @NotNull
            @QueryParam("studentId") int studentId) throws SQLException{
        System.out.println("Entered StudentId" +  studentId);
        Student student = studentInterface.fetchStudent(studentId);
        System.out.println("Received student " + student.toString());
        List<Course> registeredCourses = studentDaoInterface.viewRegisteredCourses(student);
        List<Course> result = new ArrayList<>();
        for (Course course : registeredCourses) {
            result.add(courseDaoInterface.fetchCourse(course.getCourseId()));
        }
        return result;
    }

    /**
     * Method handles API request to make payment for registered courses
     * @param studentId
     * @param P
     * @return
     */
    @POST
    @Path("/make_payment/{studentId}/{PaymentModeConstant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment(
            @NotNull
            @PathParam("studentId") int studentId ,
            @PathParam("PaymentModeConstant") int P){
        Student student = studentInterface.fetchStudent(studentId);
        double fee = paymentInterface.makePayment(student);
        registrationInterface.submitRegistration(student);
        String PaymentMode="";
        switch(P){
            case 1:
                PaymentMode="CARD";
                break;
            case 2:
                PaymentMode="E-WALLET";
                break;
            case 3:
                PaymentMode="NET-BANKING";
                break;
        }
        return Response.status(201).entity("Your payment  = " + fee+" is successful using "+ PaymentMode).build();
    }



    /**
     * Method handles request to display the total fees for student
     * @param studentId
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/calculateFees")
    public Response calculateFee(
            @NotNull
            @QueryParam("studentId") int studentId) throws SQLException{
        Student student = studentInterface.fetchStudent(studentId);
        double fee = paymentInterface.makePayment(student);
        return Response.status(200).entity("Your total fee  = " + fee + "\n").build();
    }
    /**
     * Method handles request to display the grade card for student
     * @param studentId
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/viewGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegisteredCourse> viewGradeCard(
            @NotNull
            @QueryParam("studentId") int studentId) throws SQLException{
        Student student = studentInterface.fetchStudent(studentId);
        List<RegisteredCourse> grade_card = registeredCourseDaoInterface.displayGradeCard(student);
        return grade_card;
    }
}