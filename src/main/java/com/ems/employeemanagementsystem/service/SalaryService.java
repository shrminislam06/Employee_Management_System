package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.entity.Salaries;

import com.ems.employeemanagementsystem.model.AddSalaryModel;
import com.ems.employeemanagementsystem.model.SalaryModel;
import com.ems.employeemanagementsystem.projection.SalarListProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SalaryService {
    List<Salaries> ListAll();

    void saveList(AddSalaryModel addSalaryModel);

    AddSalaryModel updateSalaryTable(long id);

    void delete(long id);

    Page<SalarListProjection> getAllEmployeeListWithSalary(String search, int pageNo, int pageSize);

    List<Salaries> GetAllSalaries(String name);
}
