package com.example.engineering.service;

import java.util.List;

import com.example.engineering.entity.Engineer;
import com.example.engineering.form.EngineerForm;

public interface EngineerService {
    
    /**
     * 全エンジニアを取得
     */
    List<Engineer> findAll();
    
    /**
     * IDでエンジニアを取得
     */
    Engineer findById(Long id);
    
    /**
     * 部署IDでエンジニアを検索
     */
    List<Engineer> findByDepartmentId(Long departmentId);
    
    /**
     * エンジニアを作成
     */
    Engineer create(EngineerForm form);
    
    /**
     * エンジニアを更新
     */
    Engineer update(Long id, EngineerForm form);
    
    /**
     * エンジニアを削除
     */
    void delete(Long id);
}