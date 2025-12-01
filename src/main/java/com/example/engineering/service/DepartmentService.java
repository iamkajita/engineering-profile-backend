package com.example.engineering.service;

import java.util.List;

import com.example.engineering.entity.Department;
import com.example.engineering.form.DepartmentForm;

public interface DepartmentService {
    
    /**
     * 全部署を取得
     */
    List<Department> findAll();
    
    /**
     * IDで部署を取得
     */
    Department findById(Long id);
    
    /**
     * 部署を作成
     */
    Department create(DepartmentForm form);
    
    /**
     * 部署を更新
     */
    Department update(Long id, DepartmentForm form);
    
    /**
     * 部署を削除
     */
    void delete(Long id);
}