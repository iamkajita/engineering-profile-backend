package com.example.engineering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.engineering.entity.Engineer;
import com.example.engineering.repository.EngineerRepository;

@Service
@Transactional
public class EngineerServiceImpl implements EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    @Override
    public List<Engineer> selectAll() {
        return engineerRepository.selectAll();
    }

    @Override
    public Engineer selectById(Long id) {
        System.out.println("EngineerService.selectById called with id: " + id);
        Engineer engineer = engineerRepository.selectById(id);
        System.out.println("Retrieved engineer: " + engineer);
        return engineer;
    }

    @Override
    public void insert(Engineer engineer) {
        engineerRepository.insert(engineer);
    }

    @Override
    public void update(Engineer engineer) {
        engineerRepository.update(engineer);
    }

    @Override
    public void delete(Long id) {
        engineerRepository.delete(id);
    }
}