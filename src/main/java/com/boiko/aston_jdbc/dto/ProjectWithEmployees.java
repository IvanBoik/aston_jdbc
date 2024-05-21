package com.boiko.aston_jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWithEmployees {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<EmployeeDTO> employees;
}
