package com.example.dto;

import com.example.model.Priority;
import com.example.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateDTO {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String assigneeEmail;

}