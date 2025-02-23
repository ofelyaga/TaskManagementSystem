package com.example.exception;

public class UserAssignmentException extends RuntimeException {
    public UserAssignmentException(String message) {
        super(message);
    }
}