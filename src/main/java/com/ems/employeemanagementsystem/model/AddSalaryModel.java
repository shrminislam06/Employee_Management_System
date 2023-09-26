package com.ems.employeemanagementsystem.model;

import lombok.*;

import java.util.Set;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddSalaryModel {
    private long id;
    Long employeesId;
    Set<Long> employeeIds;
    Long designationsId;
    Long departmentIds;
    private double basic;
    private double bonus;
    private double total;
    private String year;
    public double getBonus() {
        if(basic<20000)
        {
            bonus=basic* 10/100;
        }
        else if (basic>20000)
        {
            bonus=basic* 20/100;
        } else {
            bonus=0;
        }
        return bonus;
    }

    public double getTotal() {
        total=basic+bonus;
        return total;
    }

    public long getId() {
        return id;
    }

    public Long getEmployeesId() {
        return employeesId;
    }

    public Set<Long> getEmployeeIds() {
        return employeeIds;
    }

    public Long getDesignationsId() {
        return designationsId;
    }

    public Long getDepartmentIds() {
        return departmentIds;
    }

    public double getBasic() {
        return basic;
    }

    public String getYear() {
        return year;
    }
}
