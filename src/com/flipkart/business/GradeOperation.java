package com.flipkart.business;
import com.flipkart.DAO.RegisteredCourseDaoInterface;
import com.flipkart.DAO.RegisteredCourseDaoOperation;


public class GradeOperation implements GradeInterface{

    @Override
    public void uploadGrades(int studentId, int courseId, int marks){
        RegisteredCourseDaoInterface gradeDao= new RegisteredCourseDaoOperation();
        String grade=calculateGrade(marks);
        gradeDao.uploadGrades(studentId,courseId,grade,marks);
    }

    public String calculateGrade(int marks){
        if(marks>90)
            return "A";
        else if(marks>75)
            return "B";
        else if(marks>60)
            return "C";
        else if(marks>50)
            return "D";
        else if(marks>35)
            return "E";
        else
            return "F";
    }
}