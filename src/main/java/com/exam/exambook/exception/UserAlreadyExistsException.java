package com.exam.exambook.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message) {
        super("User Already Exists!");
    }

}
