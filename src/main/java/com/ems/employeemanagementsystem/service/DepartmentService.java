package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.model.AddDepartmentModel;

import java.util.List;

public interface DepartmentService {
    List<Department> ListAll();

    boolean saveData(AddDepartmentModel addDepartmentModel);

    AddDepartmentModel edit(Long id);

    void delete(long id);

    List<Department> GetAllDepartments(String query);
}
