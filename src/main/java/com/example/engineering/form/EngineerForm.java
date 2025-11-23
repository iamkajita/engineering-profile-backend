package com.example.engineering.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class EngineerForm {

	private Long id; // Integer NGのためLogを使用する

	@NotBlank(message = "名前を入力してください")
	@Size(max = 100, message = "名前は100文字以内で入力してください")
	private String name;

	@NotBlank(message = "名前を入力してください")
	@Size(max = 100, message = "部署名は100文字以内で入力してください")
	private String department;
	
	private String skillLevel;
	
	private String project;

	@Size(min = 0)
	private Integer yearsOfExperience; // はIntegerでOK

	private String note;

}
