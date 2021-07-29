package com.flipkart.business;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import java.util.List;
/**
 *
 * @author JEDI-Group05
 * Interface for Admin Operations
 *
 */
public interface AdminInterface {
    /**
     * Method to create Admin
     * @param admin Object storing admin details
     * @return user Id
     */
    public int createAdmin(Admin admin);
    /**
     * Method to create Professor
     * @param professor Object storing professor details
     * @return user Id
     */
    public int createProfessor(Professor professor);

    /**
     * Method to display Admin details
     */
    public List<String> displayAdmins();

    /**
     * Method to display Student details
     */
    public List<String> displayStudents();
    /**
     * Method to display Professor details
     */
    public void displayProfessors();

    /**
     * Method to display Admin details
     * @param userId userId of admin to fetch details
     */
    Admin fetchAdmin(int userId);
    /**
     * Method to get unapproved Students
     */
    List<Integer> showunapprovedStudents();
    /**
     * Method to approve given list of students
     * @param students of Student Objects
     */
    void approveStudent(List<Integer> students);
}