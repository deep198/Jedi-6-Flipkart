package com.flipkart.business;
import com.flipkart.bean.Professor;
/**
 *
 * @author JEDI-Group05
 * Interface for Professor Operations
 *
 */
public interface ProfessorInterface {
    /**
     * Method to select courses
     * @param courseId Id of course selected by professor
     * @param professor Object storing details of a Professor
     */
    void selectCourse(int courseId, Professor professor);
    /**
     * Method to display courses Professor teaches
     * @param professor Object storing details of a Professor
     */
    void displaySelectedCoursesProfessor(Professor professor);
    /**
     * Method to display available courses Professor teaches
     * @param professor Object storing details of a Professor
     */
    void displayAvailableCoursesProfessor(Professor professor);
    /**
     * Method to fetch Professor details
     * @param userId Id of professor to be fetched
     * @return Professor Object storing details of a Professor
     */
    Professor fetchProfessor(int userId);
    /**
     * Method to delete particular course which Professor teaches
     * @param courseId Id of course deselected by course
     * @param prof Object storing details of a Professor
     */
    void deleteProfessorCourse(int courseId,Professor prof);
}