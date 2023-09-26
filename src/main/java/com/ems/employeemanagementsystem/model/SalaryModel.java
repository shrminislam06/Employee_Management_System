package com.ems.employeemanagementsystem.model;

import lombok.Data;

@Data
public class SalaryModel {
private long id;
private String name;
private String employeeId;
private String designation;
private String department;
private double basic;
private  double bonus;
private double total;
private  String year;
}
