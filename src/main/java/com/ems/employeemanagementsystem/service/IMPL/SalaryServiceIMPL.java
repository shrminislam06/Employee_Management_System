package com.ems.employeemanagementsystem.service.IMPL;

import com.ems.employeemanagementsystem.entity.*;

import com.ems.employeemanagementsystem.model.AddSalaryModel;
import com.ems.employeemanagementsystem.projection.SalarListProjection;
import com.ems.employeemanagementsystem.repository.DepartmentRepository;
import com.ems.employeemanagementsystem.repository.DesignationRepostiory;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import com.ems.employeemanagementsystem.repository.SalaryRepositoy;
import com.ems.employeemanagementsystem.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalaryServiceIMPL implements SalaryService {

    private SalaryRepositoy salaryRepositoy;
    private DepartmentRepository departmentRepository;
    private DesignationRepostiory designationRepostiory;
    private EmployeeRepository employeeRepository;
@Autowired
    public SalaryServiceIMPL(SalaryRepositoy salaryRepositoy, DepartmentRepository departmentRepository, DesignationRepostiory designationRepostiory, EmployeeRepository employeeRepository) {
        this.salaryRepositoy = salaryRepositoy;
        this.departmentRepository = departmentRepository;
        this.designationRepostiory = designationRepostiory;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Salaries> ListAll() {
        return salaryRepositoy.findAll();
    }

    @Override
    public void saveList(AddSalaryModel addSalaryModel) {
       // Employee employee=employeeRepository.findById(addSalaryModel.getEmployeesId())
               // .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Department not found with this id"+addSalaryModel.getEmployeesId()));
         Set<Employee>employeeList=employeeRepository.findAllByIdIn(addSalaryModel.getEmployeeIds());
       // Department department=departmentRepository.findById(addSalaryModel.getDepartmentIds())
              //  .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Department not found with this id"+addSalaryModel.getDepartmentIds()));
        Designations designations=designationRepostiory.findById(addSalaryModel.getDesignationsId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"designation not found with this id"+addSalaryModel.getDesignationsId()));
        Set<Salaries> salarySet=employeeList.stream().map(employee -> {
            Salaries salary=new Salaries();
            salary.setEmployee(new ArrayList<>(employeeList).get(0));
            salary.setDesignations(designations);
            //salary.setDepartment(department);
            salary.setBasic(addSalaryModel.getBasic());
            salary.setBonus(addSalaryModel.getBonus());
            salary.setTotal(addSalaryModel.getTotal());
            salary.setYear(addSalaryModel.getYear());
            return salary;
        }).collect(Collectors.toSet());

salaryRepositoy.saveAll(salarySet);

    }

    @Override
    public AddSalaryModel updateSalaryTable(long id) {
    Salaries salaryList=salaryRepositoy.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"salary Id not found"+id));


    Designations designations =designationRepostiory.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"designation id not found"+id));

        Department department=departmentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"department id not found"+id));
    Set<Long>employee=salaryRepositoy.getByDepartment(department)
            .stream().map((salary)->salary.getEmployee().getId()).collect(Collectors.toSet());
    AddSalaryModel addSalaryModel=new AddSalaryModel();
    addSalaryModel.setEmployeeIds(employee);
    addSalaryModel.setDepartmentIds(id);
    addSalaryModel.setDesignationsId(id);
    addSalaryModel.setId(salaryList.getId());
    addSalaryModel.setBasic(salaryList.getBasic());
    addSalaryModel.setBonus(salaryList.getBonus());
    addSalaryModel.setTotal(salaryList.getTotal());
    addSalaryModel.setYear(salaryList.getYear());

        return addSalaryModel;
    }

    @Override
    public void delete(long id) {
    salaryRepositoy.deleteById(id);

    }

    @Override
    public Page<SalarListProjection> getAllEmployeeListWithSalary(String search, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salaryRepositoy.salaryListWithPage(search, pageable);
    }

    @Override
    public List<Salaries> GetAllSalaries(String name) {
    if(name==null||" ".equals(name))
    {
        return salaryRepositoy.findAll();
    }
        return salaryRepositoy.findSalaryByEmployeeNameContainingIgnoreCase(name);
    }



}
