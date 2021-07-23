package com.flipkart.business;
import com.flipkart.DAO.AdminDaoInterface;
import com.flipkart.DAO.AdminDaoOperation;
import com.flipkart.DAO.ProfessorDaoInterface;
import com.flipkart.DAO.ProfessorDaoOperation;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
public class AdminOperation implements AdminInterface{
    //  @Override
    //  public Student fetchStudent(String userName) {
    //      return dataAccess.getStudent(userName);
    //  }
    //   @Override
    //   public Admin fetchAdmin(String userName) {
    //       return dataAccess.getAdmin(userName);
//    }
    // @Override
    // public Professor fetchProfessor(String userName) {
    //     return dataAccess.getProfessor(userName);
    // }
    @Override
    public void createProfessor(Professor professor) {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        newProfessor.createProfessor(professor);
    }
    @Override
    public void createAdmin(Admin admin) {
        AdminDaoInterface newAdmin=new AdminDaoOperation();
        newAdmin.createAdmin(admin);
    }
    @Override
    public void displayStudents() {
        //   dataAccess.displayStudents();
        System.out.println("Display Student");
    }
    @Override
    public void displayProfessors() {
        ProfessorDaoInterface newProfessor = new ProfessorDaoOperation();
        newProfessor.displayProfessors();
    }
    @Override
    public void displayAdmins() {
        AdminDaoInterface newAdmin=new AdminDaoOperation();
        newAdmin.displayAdmins();
    }
}