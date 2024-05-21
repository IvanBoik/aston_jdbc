package com.boiko.aston_jdbc.dto;

import com.boiko.aston_jdbc.model.PassportData;
import com.boiko.aston_jdbc.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWithProjects {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private PassportData passportData;
    private String position;
    private String department;
    private List<Project> projects;
}
