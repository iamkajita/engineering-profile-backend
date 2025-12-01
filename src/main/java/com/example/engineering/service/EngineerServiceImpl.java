package com.example.engineering.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.engineering.entity.Engineer;
import com.example.engineering.form.EngineerForm;
import com.example.engineering.repository.EngineerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EngineerServiceImpl implements EngineerService {
    
    private final EngineerRepository engineerRepository;
    private final DepartmentService departmentService;
    
    private static final List<String> VALID_SKILL_LEVELS = 
            Arrays.asList("初級", "中級", "上級", "エキスパート");
    
    @Override
    public List<Engineer> findAll() {
        return engineerRepository.findAll();
    }
    
    @Override
    public Engineer findById(Long id) {
        return engineerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("エンジニアが見つかりません: ID=" + id));
    }
    
    @Override
    public List<Engineer> findByDepartmentId(Long departmentId) {
        return engineerRepository.findByDepartmentId(departmentId);
    }
    
    @Override
    @Transactional
    public Engineer create(EngineerForm form) {
        // 部署の存在チェック
        departmentService.findById(form.getDepartmentId());
        
        // バリデーション
        validateSkillLevel(form.getSkillLevel());
        
        Engineer engineer = convertToEntity(form);
        
        // デフォルト値設定
        if (engineer.getProject() == null || engineer.getProject().trim().isEmpty()) {
            engineer.setProject("待機中");
        }
        
        engineerRepository.insert(engineer);
        return findById(engineer.getId());
    }
    
    @Override
    @Transactional
    public Engineer update(Long id, EngineerForm form) {
        // 存在チェック
        findById(id);
        
        // 部署の存在チェック
        departmentService.findById(form.getDepartmentId());
        
        // バリデーション
        validateSkillLevel(form.getSkillLevel());
        
        Engineer engineer = convertToEntity(form);
        engineer.setId(id);
        
        engineerRepository.update(engineer);
        return findById(id);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        // 存在チェック
        findById(id);
        
        engineerRepository.deleteById(id);
    }
    
    /**
     * フォームからエンティティへ変換
     */
    private Engineer convertToEntity(EngineerForm form) {
        Engineer engineer = new Engineer();
        engineer.setName(form.getName());
        engineer.setDepartmentId(form.getDepartmentId());
        engineer.setProject(form.getProject());
        engineer.setSkillLevel(form.getSkillLevel());
        engineer.setYearsOfExperience(form.getYearsOfExperience());
        engineer.setNote(form.getNote());
        return engineer;
    }
    
    /**
     * スキルレベルのバリデーション
     */
    private void validateSkillLevel(String skillLevel) {
        if (skillLevel != null && !skillLevel.isEmpty() 
                && !VALID_SKILL_LEVELS.contains(skillLevel)) {
            throw new RuntimeException(
                "スキルレベルは「初級」「中級」「上級」「エキスパート」のいずれかを指定してください");
        }
    }
}