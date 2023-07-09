package com.exam.exambook.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super("User does not exists!");
    }
}
