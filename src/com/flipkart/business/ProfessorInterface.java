package com.flipkart.business;

import com.flipkart.bean.Professor;

public interface ProfessorInterface {
    void selectCourse(int courseId, Professor professor);

    void displaySelectedCoursesProfessor(Professor professor);
}
