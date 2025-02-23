package com.example.taskmanagementsystem.controller;

import com.example.dto.TaskCreateDTO;
import com.example.dto.TaskUpdateDTO;
import com.example.model.Task;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateDTO dto, @RequestHeader("X-Author-ID") UUID authorId) {
        Task task = taskService.createTask(dto, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Task>> getTasksByAuthor(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTasksByAuthor(id));
    }

    @GetMapping("/assignee/{id}")
    public ResponseEntity<List<Task>> getTasksByAssignee(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTasksByAssignee(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody TaskUpdateDTO dto) {
        Task updatedTask = taskService.updateTask(id, dto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}