package com.ems.employeemanagementsystem.service.IMPL;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Designations;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.model.AddDepartmentModel;
import com.ems.employeemanagementsystem.repository.DepartmentRepository;
import com.ems.employeemanagementsystem.repository.DesignationRepostiory;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import com.ems.employeemanagementsystem.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceIMPL implements DepartmentService {
private DesignationRepostiory designationRepostiory;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    public DepartmentServiceIMPL(DesignationRepostiory designationRepostiory,DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository= employeeRepository;
        this.designationRepostiory=designationRepostiory;
    }

    @Override
    public List<Department> ListAll() {
        return departmentRepository.findAll();
    }

    @Override
    public boolean saveData(AddDepartmentModel addDepartmentModel) {
        Set<Employee> employeeSet = employeeRepository.findAllByIdIn(addDepartmentModel.getEmployeeIds());
           Department department=new Department();
           department.setId(addDepartmentModel.getId());
           department.setDeptName(addDepartmentModel.getDeptName());
           department.setEmployees(employeeSet);
           departmentRepository.save(department);
      return true;

    }

    @Override
    public AddDepartmentModel edit(Long id ) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"dept id not found"+id));
        Set<Long> employee = department.getEmployees().stream().map(Employee::getId).collect(Collectors.toSet());

        AddDepartmentModel departmentModel = new AddDepartmentModel();
       departmentModel.setId(department.getId());
        departmentModel.setDeptName(department.getDeptName());
        departmentModel.setEmployeeIds(employee);

        return departmentModel;
    }

    @Override
    public void delete(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> GetAllDepartments(String deptName) {

        if(deptName==null||" ".equals(deptName)){

            return departmentRepository.findAll();
        }else {
        return departmentRepository.findAllDepartmentByDeptNameContainingIgnoreCase(deptName);
    }
}
}
