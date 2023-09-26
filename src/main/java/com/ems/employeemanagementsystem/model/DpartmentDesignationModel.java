package com.ems.employeemanagementsystem.model;

import com.ems.employeemanagementsystem.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DpartmentDesignationModel {

    private long id;
    private String name;
    private String designations;
    private String deptName;

}
