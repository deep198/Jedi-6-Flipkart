package com.flipkart.DAO;
import com.flipkart.bean.Professor;
/*
 *
 * @author JEDI-Group05
 * Interface for Course Operations
 *
 */
public interface ProfessorDaoInterface {
    /**
     * Method to create Professor using SQL Command
     *
     * @param professor object :which contains the details of the Professor
     * @return Professor ID
     */
    public int createProfessor(Professor professor);

    /**
     * Method to display list of Professors using SQL Command
     */
    public void displayProfessors();

    /**
     * Method to get the details of the Professor from particular Professor ID
     *
     * @param UserID :Similar to Professor Id
     * @return Professor Object
     */
    Professor fetchProfessor(int UserID);
}