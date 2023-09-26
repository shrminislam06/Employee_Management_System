package com.ems.employeemanagementsystem.repository;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findById(long id);
    Set<Employee>findAllByIdIn(Set<Long>employeeIds);
    Optional<Employee>findAllEmployeeByIdIn(Set<Long>employeeIds);
    List<Employee>findByNameContainingIgnoreCase(String keywords);

    List<Employee> getEmployeeByNameContainingIgnoreCase(String empName);


    Employee getEmployeeByName(String name);

    List<Employee> getAllEmployeeByNameContainingIgnoreCase(String name);

    Optional<Object> findEmployeeByEmployeeId(String employeeId);

    //<Employee> findDepartmentByEmployeeName(Set<Long> employeeIds);

    //Optional<Employee> findDepartmentByName(Set<Long> employeeIds);

    Optional<Employee> findByName(String name);
}
