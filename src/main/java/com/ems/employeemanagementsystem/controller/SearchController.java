package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.repository.DepartmentRepository;
import com.ems.employeemanagementsystem.repository.EmployeeRepository;
import com.ems.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {
@Autowired
private EmployeeService employeeService;
@Autowired
private DepartmentRepository departmentRepository;

@GetMapping("/search")
public String search(Model model){
    model.addAttribute("searchPage");
    return "search";
}




}
