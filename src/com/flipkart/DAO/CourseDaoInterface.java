package com.flipkart.DAO;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;


public interface CourseDaoInterface {
    public List<Course> displayCourses(Student student);
    public void insertCourse(Course course) ;
    public void deleteCourse(int courseId) throws CourseNotFoundException;
    public List<Course> displayCoursesProfessor();
    public void deleteProfessorCourse(int courseId, int ProfessorID);

}