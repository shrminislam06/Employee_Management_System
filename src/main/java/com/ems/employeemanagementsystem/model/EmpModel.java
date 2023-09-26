package com.ems.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpModel {

    private String employeeId;
    private String name;
    private String phone;
    private String email;
    private String joinDate;
    private String city;
    private String hometown;
    private String village;
}
