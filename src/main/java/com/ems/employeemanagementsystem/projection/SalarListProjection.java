package com.ems.employeemanagementsystem.projection;

public interface SalarListProjection {

   long getId();
    String getName();
    String getEmployeeId();
    String getDesignation();
    String getDeptName();
    double getBasic();
    double getBonus();
    double getTotal();
    String getYear();

}
