package com.flipkart.DAO;

import com.flipkart.bean.Course;

import java.util.List;

public class TemporaryClass {

        public static void main(String args[]) {
//                User user = new User();
//                user.setUserId(101);
//                user.setUserName("Prav");
//                user.setUserRole("Prof");
//                user.setPassword("pass");
//
//                UserDaoInterface userDaoInterface = new UserDaoOperation();
//                userDaoInterface.createUser(user);
//
//                User user2 = new User();
//                user2.setUserId(101);
//                user2.setUserName("Pravalli");
//                user2.setUserRole("Prof");
//                user2.setPassword("pass");
//
//                userDaoInterface.updateUser(user2);
//
//
//                User checkedUserTst = userDaoInterface.validateUser(101, "pass");
//                System.out.println(checkedUserTst.getUserName());


//                Admin admin = new Admin();
//                admin.setName("Admin6");
//                AdminDaoInterface adminDao = new AdminDaoOperation();
//                adminDao.createAdmin(admin);
//                adminDao.displayAdmins();
//
//                Professor professor = new Professor();
//                professor.setName("Prof23");
//                professor.setDepartment("ECE");
//                Professor professor2 = new Professor();
//                professor2.setName("Prof123");
//                professor2.setDepartment("ECE");
//                ProfessorDaoInterface professorDao = new ProfessorDaoOperation();
//                professorDao.createProfessor(professor2);
//                professorDao.createProfessor(professor);
//                professorDao.displayProfessors();

//                StudentDaoInterface studentDaoInterface = new StudentDaoOperation();
//                List<Integer> students = new ArrayList<>();
//                students.add(1003);
//                students.add(1004);
//                studentDaoInterface.approveStudent(students);

//                Professor professor = new Professor();
//                professor.setName("Prof1213");
//                professor.setDepartment("ECE");
//                ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();
//                System.out.println("Prof Id" + professorDaoInterface.createProfessor(professor));

                CourseDaoInterface coureDAO = new CourseDaoOperation();
//                Course course = new Course();
//                course.setCourseId(101);
//                course.setCourseName("C++");
//                course.setDepartment("CSE");
//                course.setSem(1);
//                course.setCredits(3);
//                coureDAO.insertCourse(course);
                List<Course> course = coureDAO.displayCoursesProfessor();
                for (Course course1 : course) {
                        System.out.println(course1.getCourseId() + "   " +  course1.getCourseName());
                }

                coureDAO.deleteProfessorCourse(101, 101);

        }

}
