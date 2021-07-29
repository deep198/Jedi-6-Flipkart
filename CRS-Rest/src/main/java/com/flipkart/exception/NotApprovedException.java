package com.flipkart.exception;

public class NotApprovedException extends Exception {

    public String getMessage() {
        String msg="You won't be able to login until Admin approves your Registration..";
        return msg;
    }
}