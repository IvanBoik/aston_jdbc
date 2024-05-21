package com.boiko.aston_jdbc.dto;

import com.boiko.aston_jdbc.model.PassportData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private PassportData passportData;
    private String position;
    private String department;
}
