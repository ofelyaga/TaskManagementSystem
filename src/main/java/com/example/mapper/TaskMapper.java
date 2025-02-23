package com.example.mapper;

import com.example.dto.TaskCreateDTO;
import com.example.model.Task;
import com.example.model.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskCreateDTO dto, User author, User assignee) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setAuthor(author);
        task.setAssignee(assignee);
        return task;
    }
}