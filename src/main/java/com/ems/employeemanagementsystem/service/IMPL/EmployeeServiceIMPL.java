package com.ems.employeemanagementsystem.service.IMPL;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.model.AddEmployeeModel;
import com.ems.employeemanagementsystem.repository.DepartmentRepository;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import com.ems.employeemanagementsystem.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeServiceIMPL(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Employee> GetAllEployees(String empName) {

        if (empName == null || "".equals(empName)) {
           // log.info("Employee list reading : "+ employeeRepository.findAll().size());
            return employeeRepository.findAll();
        } else {
           // log.info("Employee list reading : "+employeeRepository.getEmployeeByNameContainingIgnoreCase(empName).size());
            return employeeRepository.getEmployeeByNameContainingIgnoreCase(empName);
        }


    }

//    @Override
//    public Employee GetAllEployees(String empName) {
//        return employeeRepository.getEmployeeByName(empName);
//    }

    @Override
    public List<Employee> GetAllEployee() {

        return employeeRepository.findAll();

    }


    @Override
    public boolean saveEmployee(Employee employee) {
        employeeRepository.save(employee);

return true;

    }

    @Override
    public Employee EditEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void DeleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> AddEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getbyid(long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List <Employee> getEmployeeByname(String name) {

        if(name==null)
        {
            return employeeRepository.findAll();
        }else
        {
            return employeeRepository.getAllEmployeeByNameContainingIgnoreCase(name);
        }


    }
}
