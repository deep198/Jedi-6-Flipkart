package com.flipkart.DAO;
import com.flipkart.bean.Professor;

public interface ProfessorDaoInterface {
    public int createProfessor(Professor professor);
    public void displayProfessors();

    Professor fetchProfessor(int UserID);
}