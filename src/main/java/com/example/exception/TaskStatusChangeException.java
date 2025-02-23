package com.example.exception;

public class TaskStatusChangeException extends RuntimeException {
    public TaskStatusChangeException(String message) {
        super(message);
    }
}