package com.ems.employeemanagementsystem.service;

import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.model.AddEmployeeModel;

import java.util.List;

public interface EmployeeService {
    List<Employee> GetAllEployees(String empName);
    List<Employee> GetAllEployee();

    boolean saveEmployee(Employee employee);

    Employee EditEmployee(Long id);

    void DeleteEmployee(Long id);

    List<Employee> AddEmployee();

    Employee getbyid(long id);

   List <Employee> getEmployeeByname(String name);


}
