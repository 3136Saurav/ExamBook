package com.exam.exambook.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super("Invalid Credentials");
    }
}
