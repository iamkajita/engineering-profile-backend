package com.example.engineering.entity;

import lombok.Data;

@Data
public class Engineer {
    private Long id;
    private String name;
    private String department;
    private String project;
    private String skillLevel;
    private Integer yearsOfExperience;
    private String note;
}