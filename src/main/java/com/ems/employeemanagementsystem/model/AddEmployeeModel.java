package com.ems.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddEmployeeModel {
    long id;
    String name;
    String employeeId;
    Set<Long> departmentIds;
}
