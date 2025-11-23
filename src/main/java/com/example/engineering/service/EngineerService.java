package com.example.engineering.service;
import java.util.List;

import com.example.engineering.entity.Engineer;

public interface EngineerService {
    List<Engineer> selectAll();
    Engineer selectById(Long id);  // Integer はNG　Long に変更
    void insert(Engineer engineer);
    void update(Engineer engineer);
    void delete(Long id);  // Integer はNG　Long に変更
}