package com.flipkart.exception;

public class IncorrectOldPassword extends Exception {

public String getMessage() {
        String msg = "Incorrect Old Password!!";
        return msg;
        }
}
