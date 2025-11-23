package com.example.engineering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.engineering.entity.Engineer;
import com.example.engineering.service.EngineerService;

@RestController
@RequestMapping("/api/engineers")
@CrossOrigin(origins = "http://localhost:4200")
public class EngineerController {
    
    @Autowired
    private EngineerService engineerService;
    
    @GetMapping
    public ResponseEntity<List<Engineer>> getAllEngineers() {
        try {
            List<Engineer> engineers = engineerService.selectAll();
            return ResponseEntity.ok(engineers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Engineer> getEngineerById(@PathVariable Long id) {  // ← Long型に変更
        try {
            System.out.println("Controller: Getting engineer with id: " + id);
            Engineer engineer = engineerService.selectById(id);
            
            if (engineer == null) {
                System.out.println("Engineer not found with id: " + id);
                return ResponseEntity.notFound().build();
            }
            
            System.out.println("Controller: Found engineer: " + engineer);
            return ResponseEntity.ok(engineer);
        } catch (Exception e) {
            System.err.println("Error in getEngineerById:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Void> createEngineer(@RequestBody Engineer engineer) {
        try {
            System.out.println("Creating engineer: " + engineer);
            engineerService.insert(engineer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEngineer(@PathVariable Long id, @RequestBody Engineer engineer) {  // ← Long型に変更
        try {
            engineer.setId(id);
            engineerService.update(engineer);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Long id) {  // ← Long型に変更
        try {
            engineerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}