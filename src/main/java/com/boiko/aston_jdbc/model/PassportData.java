package com.boiko.aston_jdbc.model;

import lombok.*;

import java.time.LocalDate;

@Data
public class PassportData {
    private Long id;
    private String series;
    private String number;
    private LocalDate dateOfIssue;
    private String departmentCode;
    private String issuingAuthority;
}
