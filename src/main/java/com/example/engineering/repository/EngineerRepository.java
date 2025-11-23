package com.example.engineering.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.engineering.entity.Engineer;

@Mapper
public interface EngineerRepository {
    
    List<Engineer> selectAll();
    
    Engineer selectById(@Param("id") Long id);  // ← @Paramを追加
    
    void insert(Engineer engineer);
    
    void update(Engineer engineer);
    
    void delete(@Param("id") Long id);  // ← @Paramを追加
}