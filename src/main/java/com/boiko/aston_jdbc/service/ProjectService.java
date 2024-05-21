package com.boiko.aston_jdbc.service;

import com.boiko.aston_jdbc.dto.EmployeeDTO;
import com.boiko.aston_jdbc.dto.ProjectWithEmployees;
import com.boiko.aston_jdbc.model.Project;
import com.boiko.aston_jdbc.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;

    public boolean add(Project project) {
        return projectRepository.insert(project);
    }

    public boolean update(Project project) {
        return projectRepository.update(project);
    }

    public boolean delete(Long id) {
        return projectRepository.delete(id);
    }

    public Project getByID(Long id) {
        return projectRepository.getByID(id);
    }

    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    public List<ProjectWithEmployees> getAllProjectsWithEmployees() {
        List<Project> projects = projectRepository.getAll();
        List<ProjectWithEmployees> dtos = new ArrayList<>();
        for (Project p : projects) {
            dtos.add(modelToProjectWithEmployees(p));
        }
        return dtos;
    }

    private ProjectWithEmployees modelToProjectWithEmployees(Project project) {
        List<EmployeeDTO> employees = employeeService.getAllByProjectID(project.getId());
        return new ProjectWithEmployees(
                project.getId(),
                project.getName(),
                project.getStartDate(),
                project.getEndDate(),
                employees
        );
    }

    public ProjectWithEmployees getProjectWithEmployeesByID(Long id) {
        Project project = projectRepository.getByID(id);
        return modelToProjectWithEmployees(project);
    }

    public List<Project> getAllByEmployeeID(Long id) {
        return projectRepository.getAllByEmployeeID(id);
    }
}
