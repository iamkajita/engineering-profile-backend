package com.example.engineering.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.engineering.entity.Department;
import com.example.engineering.form.DepartmentForm;
import com.example.engineering.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    
    private final DepartmentService departmentService;
    
    /**
     * 全部署を取得
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }
    
    /**
     * IDで部署を取得
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        try {
            Department department = departmentService.findById(id);
            return ResponseEntity.ok(department);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 部署を作成
     */
    @PostMapping
    public ResponseEntity<?> createDepartment(
            @Validated @RequestBody DepartmentForm form,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse(getFirstErrorMessage(bindingResult)));
        }
        
        try {
            Department department = departmentService.create(form);
            return ResponseEntity.status(HttpStatus.CREATED).body(department);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 部署を更新
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable Long id,
            @Validated @RequestBody DepartmentForm form,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse(getFirstErrorMessage(bindingResult)));
        }
        
        try {
            Department department = departmentService.update(id, form);
            return ResponseEntity.ok(department);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 部署を削除
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "部署を削除しました");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * エラーレスポンスを作成
     */
    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }
    
    /**
     * バリデーションエラーの最初のメッセージを取得
     */
    private String getFirstErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().get(0).getDefaultMessage();
    }
}