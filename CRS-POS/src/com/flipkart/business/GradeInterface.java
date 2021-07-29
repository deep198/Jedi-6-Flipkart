package com.flipkart.business;
/**
 *
 * @author JEDI-Group05
 * Interface for Grades
 *
 */
public interface GradeInterface {
    /**
     * Method to upload grades used by Professor
     * @param studentId ID of student for whom grades are to be uploaded
     * @param courseId ID of course for which grade is to be uploaded
     * @param marks marks for given course for given student
     */
    void uploadGrades(int studentId, int courseId, int marks);
}