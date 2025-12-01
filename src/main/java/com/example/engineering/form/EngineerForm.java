package com.example.engineering.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class EngineerForm {
    
    @NotBlank(message = "名前を入力してください")
    @Size(max = 100, message = "名前は100文字以内で入力してください")
    private String name;
    
    @NotNull(message = "部署を選択してください")
    private Long departmentId;
    
    @Size(max = 20, message = "プロジェクト名は20文字以内で入力してください")
    private String project;
    
    private String skillLevel;
    
    @Min(value = 0, message = "経験年数は0以上で入力してください")
    private Integer yearsOfExperience;
    
    private String note;
}