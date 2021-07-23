package com.flipkart.DAO;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.List;
public interface RegisteredCourseDaoInterface {

    public List<RegisteredCourse> displayGradeCard(Student student);
    void uploadGrades(int studentId, int courseId, String grade, int marks);
}