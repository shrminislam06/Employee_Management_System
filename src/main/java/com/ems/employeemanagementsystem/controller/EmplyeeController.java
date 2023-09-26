package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.model.AddEmployeeModel;
import com.ems.employeemanagementsystem.service.DepartmentService;
import com.ems.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("/employee")
public class EmplyeeController {
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    public EmplyeeController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = ("/search"), method = RequestMethod.GET)
    public String getEmployeeByname(@ModelAttribute("employee") Employee employee, Model model,
                                    @RequestParam(value = "query", defaultValue = "", required = false) String query) {
        List<Employee> employeeList = employeeService.GetAllEployees(query);


        model.addAttribute("employeeList", employeeList);
        return "employee";

    }

    @GetMapping("/Allemployee")
    public String Employee(Model model) {
        List<Employee> employeeList = employeeService.GetAllEployee();
        List<Department>departmentList=departmentService.ListAll();
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("employeeList", employeeList);

        return "employee";
    }

    @RequestMapping(value = ("/addEmployee"), method = RequestMethod.GET)
    public String AddEmployee(Model model) {
        List<Employee> employeeList = employeeService.GetAllEployee();
        List<Department>departmentList=departmentService.ListAll();
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute(("employee"), new Employee());
        return "addEmployee";
    }

    @RequestMapping(value = ("/save"), method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employee/Allemployee";
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView EditEmployee(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("addEmployee");
        Employee employee = employeeService.EditEmployee(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String Delete(@PathVariable("id") Long id) {
        employeeService.DeleteEmployee(id);
        return "redirect:/employee/Allemployee";
    }

    @GetMapping("/getByid/{id}")
    public Employee getbyid(@RequestParam("id") Long id) {
        return employeeService.getbyid(id);
    }


}
