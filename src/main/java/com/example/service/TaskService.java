package com.example.service;

import com.example.dto.TaskCreateDTO;
import com.example.dto.TaskUpdateDTO;
import com.example.mapper.TaskMapper;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Transactional
    public Task createTask(TaskCreateDTO dto, UUID authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        User assignee = null;
        if (dto.getAssigneeEmail() != null) {
            assignee = userRepository.findByEmail(dto.getAssigneeEmail())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
        }

        Task task = taskMapper.toEntity(dto, author, assignee);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByAuthor(UUID authorId) {
        return taskRepository.findByAuthorId(authorId);
    }

    public List<Task> getTasksByAssignee(UUID assigneeId) {
        return taskRepository.findByAssigneeId(assigneeId);
    }

    public Optional<Task> getTaskById(UUID taskId) {
        return taskRepository.findById(taskId);
    }

    @Transactional
    public Task updateTask(UUID taskId, TaskUpdateDTO dto) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (dto.getTitle() != null) {
            existingTask.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            existingTask.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            existingTask.setStatus(dto.getStatus());
        }
        if (dto.getPriority() != null) {
            existingTask.setPriority(dto.getPriority());
        }
        if (dto.getAssigneeEmail() != null) {
            User assignee = userRepository.findByEmail(dto.getAssigneeEmail())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
            existingTask.setAssignee(assignee);
        }

        return taskRepository.save(existingTask);
    }

    @Transactional
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}