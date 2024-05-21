package com.boiko.aston_jdbc.controller;

import com.boiko.aston_jdbc.dto.ProjectWithEmployees;
import com.boiko.aston_jdbc.model.Project;
import com.boiko.aston_jdbc.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<Boolean> add(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.add(project));
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> update(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.update(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getByID(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{id}/with_employees")
    public ResponseEntity<ProjectWithEmployees> getProjectWithEmployeesByID(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectWithEmployeesByID(id));
    }

    @GetMapping("/with_projects")
    public ResponseEntity<List<ProjectWithEmployees>> getAllProjectsWithEmployees() {
        return ResponseEntity.ok(projectService.getAllProjectsWithEmployees());
    }
}
