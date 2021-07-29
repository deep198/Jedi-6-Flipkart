package com.flipkart.exception;

public class CourseNotFoundException extends Exception{
    public String getMessage() {
        String msg="No such course exists!";
        return msg;
    }

}
