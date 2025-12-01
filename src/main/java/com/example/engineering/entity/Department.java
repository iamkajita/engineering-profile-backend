package com.example.engineering.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Department {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}