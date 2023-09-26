package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Designations;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.model.AddDepartmentModel;
import com.ems.employeemanagementsystem.model.DpartmentDesignationModel;
import com.ems.employeemanagementsystem.projection.DepartmentsDesignation;
import com.ems.employeemanagementsystem.repository.DepartmentRepository;
import com.ems.employeemanagementsystem.service.DepartmentService;
import com.ems.employeemanagementsystem.service.DesignationService;
import com.ems.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentsController {

    private DepartmentService departmentService;
    private EmployeeService employeeService;
private DesignationService designationService;
    private DepartmentRepository departmentRepository;


    public DepartmentsController(DesignationService designationService,DepartmentService departmentService, EmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
        this.designationService=designationService;
    }

    @RequestMapping("/all")
    public String viewpage(Model model){
        List<DpartmentDesignationModel>Designations=new ArrayList<>();
        for (DepartmentsDesignation departmentsEmployee:departmentRepository.departmentsList())
        {
            DpartmentDesignationModel dpartment=new DpartmentDesignationModel();
            dpartment.setId(departmentsEmployee.getId());
            dpartment.setName(departmentsEmployee.getName());
            dpartment.setDesignations(departmentsEmployee.getDesignations());
            dpartment.setDeptName(departmentsEmployee.getDeptName());
            Designations.add(dpartment);
        }
        model.addAttribute("departmentList",Designations);
        return "department";

    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String AddDepartment(Model model){
        List<Employee> employeeList=employeeService.GetAllEployee();
        List<Department>departmentList=departmentService.ListAll();
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("department",new AddDepartmentModel());
        return "add";
    }

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public String submitData(@ModelAttribute("department")AddDepartmentModel addDepartmentModel){
        departmentService.saveData(addDepartmentModel);
        return "redirect:/department/all";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView modelAndView=new ModelAndView("add");
        List<Employee>employeeList=employeeService.GetAllEployee();
        AddDepartmentModel addDepartmentModel=departmentService.edit(id);
        modelAndView.addObject("department",addDepartmentModel);
        modelAndView.addObject("employeeList",employeeList);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") long id){
        departmentService.delete(id);
        return "redirect:/department/all";
    }
//    @GetMapping("/search")
//    public String searchDepartmentydeptname(@ModelAttribute("department")AddDepartmentModel addDepartmentModel, Model model,
//                                            @RequestParam(value = "query",defaultValue = " ",required = false) String query)
//    {
//        List<Department>departmentList=departmentService.GetAllDepartments(query);
//       List <Employee>employeeList=employeeService.GetAllEployees(query);
//        model.addAttribute("departmentList",departmentList);
//        model.addAttribute("employeeList",employeeList);
//        return "department";
//    }





}
