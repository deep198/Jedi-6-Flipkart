package com.flipkart.business;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
public interface AdminInterface {
    public void createAdmin(Admin admin);
    public void createProfessor(Professor professor);
    public void displayAdmins();
    public void displayStudents();
    public void displayProfessors();
}