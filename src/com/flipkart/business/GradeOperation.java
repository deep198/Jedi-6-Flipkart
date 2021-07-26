package com.flipkart.business;

import com.flipkart.DAO.RegisteredCourseDaoInterface;
import com.flipkart.DAO.RegisteredCourseDaoOperation;

/**
 *
 * @author JEDI-Group05
 * Implementation of Grade Operations
 *
 */
public class GradeOperation implements GradeInterface{
    /**
     * Method to upload grades used by Professor
     * @param studentId ID of student for whom grades are to be uploaded
     * @param courseId ID of course for which grade is to be uploaded
     * @param marks marks for given course for given student
     */
    @Override
    public void uploadGrades(int studentId, int courseId, int marks){
        RegisteredCourseDaoInterface gradeDao= new RegisteredCourseDaoOperation();
        String grade=calculateGrade(marks);
        gradeDao.uploadGrades(studentId,courseId,grade,marks);
    }
    /**
     * Method to calculate grades
     * @param marks marks to get grade for
     * @return grade
     */
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