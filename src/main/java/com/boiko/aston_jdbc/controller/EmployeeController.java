package com.boiko.aston_jdbc.controller;

import com.boiko.aston_jdbc.dto.EmployeeDTO;
import com.boiko.aston_jdbc.dto.EmployeeWithProjects;
import com.boiko.aston_jdbc.model.Employee;
import com.boiko.aston_jdbc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<Boolean> add(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.add(employee));
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> update(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getByID(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}/with_projects")
    public ResponseEntity<EmployeeWithProjects> getEmployeeWithProjectsByID(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeWithProjectsByID(id));
    }

    @GetMapping("/with_projects")
    public ResponseEntity<List<EmployeeWithProjects>> getAllEmployeesWithProjects() {
        return ResponseEntity.ok(employeeService.getAllEmployeesWithProjects());
    }
}
