package com.flipkart.business;
import com.flipkart.DAO.StudentDaoInterface;
import com.flipkart.DAO.StudentDaoOperation;
import com.flipkart.bean.Student;

/**
 *
 * @author JEDI-Group05
 * Implementations of Payment Operations
 */
public class PaymentOperation implements PaymentInterface{
    /**
     * Method to get Payment Status
     * @param studentId ID of student to get payment status
     * @return Payment status
     */
    @Override
    public boolean getPaymentStatus(int studentId) {
        StudentDaoInterface studentDao = new StudentDaoOperation();
        return studentDao.getPaymentStatus(studentId);
    }
    /**
     * Method to make Payment
     * @param student Object storing student details
     * @return fee based on Student details
     */
    @Override
    public double makePayment(Student student) {
        int studentSem=student.getSem();
        double fee = 0;
        while(true){
            switch(studentSem){
                case 1:
                    fee=200000;
                    break;
                case 2:
                    fee=210000;
                    break;
                case 3:
                    fee=220000;
                    break;
                case 4:
                    fee=230000;
                    break;
                case 5:
                    fee=260000;
                    break;
                case 6:
                    fee=280000;
                    break;
                case 7:
                    fee=190000;
                    break;
                case 8:
                    fee=250000;
                    break;
                default:
                    System.out.println("Please enter values 1-8 ");
            }
            if(studentSem>0 && studentSem<9) {
                break;
            }
        }
        return fee;
    }
}