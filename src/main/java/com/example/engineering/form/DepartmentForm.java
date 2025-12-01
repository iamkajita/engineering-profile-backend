package com.example.engineering.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class DepartmentForm {
    
    @NotBlank(message = "部署名を入力してください")
    @Size(max = 100, message = "部署名は100文字以内で入力してください")
    private String name;
}