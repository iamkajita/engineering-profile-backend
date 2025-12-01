package com.example.engineering.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.engineering.entity.Department;
import com.example.engineering.form.DepartmentForm;
import com.example.engineering.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
    
    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("部署が見つかりません: ID=" + id));
    }
    
    @Override
    @Transactional
    public Department create(DepartmentForm form) {
        // 部署名の重複チェック
        departmentRepository.findByName(form.getName()).ifPresent(dept -> {
            throw new RuntimeException("既に存在する部署名です: " + form.getName());
        });
        
        Department department = new Department();
        department.setName(form.getName());
        
        departmentRepository.insert(department);
        return department;
    }
    
    @Override
    @Transactional
    public Department update(Long id, DepartmentForm form) {
        // 存在チェック
        Department existing = findById(id);
        
        // 部署名の重複チェック（自分以外）
        if (departmentRepository.countByNameExcludingId(form.getName(), id) > 0) {
            throw new RuntimeException("既に存在する部署名です: " + form.getName());
        }
        
        existing.setName(form.getName());
        departmentRepository.update(existing);
        
        return findById(id);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        // 存在チェック
        findById(id);
        
        departmentRepository.deleteById(id);
    }
}