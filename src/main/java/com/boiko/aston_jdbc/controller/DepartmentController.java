package com.boiko.aston_jdbc.controller;

import com.boiko.aston_jdbc.model.Department;
import com.boiko.aston_jdbc.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    @PostMapping("/")
    public ResponseEntity<Boolean> add(@RequestBody String departmentName) {
        Department department = new Department();
        department.setName(departmentName);
        return ResponseEntity.ok(departmentRepository.insert(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody String departmentName) {
        Department department = new Department(id, departmentName);
        return ResponseEntity.ok(departmentRepository.update(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(departmentRepository.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(departmentRepository.getByID(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentRepository.getAll());
    }
}
