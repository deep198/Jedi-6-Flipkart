package com.flipkart.business;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;

import java.util.List;

public interface AdminInterface {
    public int createAdmin(Admin admin);
    public int createProfessor(Professor professor);
    public void displayAdmins();
    public void displayStudents();
    public void displayProfessors();

    Admin fetchAdmin(int userId);

    void showunapprovedStudents();

    void approveStudent(List<Integer> students);
}