package com.boiko.aston_jdbc.model;

import lombok.*;

@Data
public class Employee {
    private Long id;
    private Long passportID;
    private Long positionID;
    private Long departmentID;
    private String name;
    private String surname;
    private String email;
    private String phone;
}
