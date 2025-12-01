package com.example.engineering.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.engineering.entity.Engineer;

@Mapper
public interface EngineerRepository {
    
    /**
     * 全エンジニアを取得（部署名付き）
     */
    List<Engineer> findAll();
    
    /**
     * IDでエンジニアを取得（部署名付き）
     */
    Optional<Engineer> findById(@Param("id") Long id);
    
    /**
     * 部署IDでエンジニアを検索（部署名付き）
     */
    List<Engineer> findByDepartmentId(@Param("departmentId") Long departmentId);
    
    /**
     * エンジニアを作成
     */
    void insert(Engineer engineer);
    
    /**
     * エンジニアを更新
     */
    void update(Engineer engineer);
    
    /**
     * エンジニアを削除
     */
    void deleteById(@Param("id") Long id);
}