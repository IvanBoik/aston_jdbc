package com.boiko.aston_jdbc.service;

import com.boiko.aston_jdbc.dto.EmployeeDTO;
import com.boiko.aston_jdbc.dto.EmployeeWithProjects;
import com.boiko.aston_jdbc.model.*;
import com.boiko.aston_jdbc.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PassportRepository passportRepository;
    private final PositionRepository positionRepository;
    private final ProjectRepository projectRepository;
    
    public boolean add(Employee employee) {
        return employeeRepository.insert(employee);
    }
    
    public boolean update(Employee employee) {
        return employeeRepository.update(employee);
    }

    public boolean delete(Long id) {
        return employeeRepository.delete(id);
    }
    
    public EmployeeDTO getByID(Long id) {
        Employee employee = employeeRepository.getByID(id);
        return modelToDTO(employee);
    }

    private EmployeeDTO modelToDTO(Employee employee) {
        Department department = departmentRepository.getByID(employee.getDepartmentID());
        PassportData passportData = passportRepository.getByID(employee.getPassportID());
        Position position = positionRepository.getByID(employee.getPositionID());

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getEmail(),
                employee.getPhone(),
                passportData,
                position.getName(),
                department.getName()
        );
    }

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.getAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e : employees) {
            employeeDTOS.add(modelToDTO(e));
        }
        return employeeDTOS;
    }

    public List<EmployeeDTO> getAllByProjectID(Long id) {
        List<Employee> employees = employeeRepository.getAllByProjectID(id);
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee e : employees) {
            dtos.add(modelToDTO(e));
        }
        return dtos;
    }

    public List<EmployeeWithProjects> getAllEmployeesWithProjects() {
        List<Employee> employees = employeeRepository.getAll();
        List<EmployeeWithProjects> dtos = new ArrayList<>();
        for (Employee e : employees) {
            dtos.add(modelToEmployeeWithProjects(e));
        }
        return dtos;
    }

    private EmployeeWithProjects modelToEmployeeWithProjects(Employee employee) {
        Department department = departmentRepository.getByID(employee.getDepartmentID());
        PassportData passportData = passportRepository.getByID(employee.getPassportID());
        Position position = positionRepository.getByID(employee.getPositionID());
        List<Project> projects = projectRepository.getAllByEmployeeID(employee.getId());

        return new EmployeeWithProjects(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getEmail(),
                employee.getPhone(),
                passportData,
                position.getName(),
                department.getName(),
                projects
        );
    }

    public EmployeeWithProjects getEmployeeWithProjectsByID(Long id) {
        Employee employee = employeeRepository.getByID(id);
        return modelToEmployeeWithProjects(employee);
    }
}
