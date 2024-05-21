package com.boiko.aston_jdbc.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Project {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
