package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String email;
    private String password;
    private String role; // "ADMIN", "USER"

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Task> createdTasks = new ArrayList<>();

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Task> assignedTasks = new ArrayList<>();
}