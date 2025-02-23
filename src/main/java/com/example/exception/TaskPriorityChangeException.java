package com.example.exception;

public class TaskPriorityChangeException extends RuntimeException {
    public TaskPriorityChangeException(String message) {
        super(message);
    }
}