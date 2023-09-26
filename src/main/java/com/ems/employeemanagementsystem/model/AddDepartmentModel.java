package com.ems.employeemanagementsystem.model;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;
@Data

@AllArgsConstructor
@ToString
@NoArgsConstructor

public class AddDepartmentModel {

long id;
    private String deptName;
    private Set<Long >employeeIds;
    private Set<Long> designationIds;

}
