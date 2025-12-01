package com.example.engineering.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.engineering.entity.Department;

@Mapper
public interface DepartmentRepository {
    
    /**
     * 全部署を取得
     */
    List<Department> findAll();
    
    /**
     * IDで部署を取得
     */
    Optional<Department> findById(@Param("id") Long id);
    
    /**
     * 部署名で部署を取得
     */
    Optional<Department> findByName(@Param("name") String name);
    
    /**
     * 部署を作成
     */
    void insert(Department department);
    
    /**
     * 部署を更新
     */
    void update(Department department);
    
    /**
     * 部署を削除
     */
    void deleteById(@Param("id") Long id);
    
    /**
     * 部署名の重複チェック（自分以外）
     */
    int countByNameExcludingId(@Param("name") String name, @Param("id") Long id);
}