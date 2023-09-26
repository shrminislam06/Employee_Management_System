package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.entity.Department;
import com.ems.employeemanagementsystem.entity.Designations;
import com.ems.employeemanagementsystem.entity.Employee;
import com.ems.employeemanagementsystem.entity.Salaries;

import com.ems.employeemanagementsystem.model.AddSalaryModel;
import com.ems.employeemanagementsystem.model.SalaryModel;
import com.ems.employeemanagementsystem.projection.SalarListProjection;
import com.ems.employeemanagementsystem.repository.SalaryRepositoy;
import com.ems.employeemanagementsystem.service.DepartmentService;
import com.ems.employeemanagementsystem.service.DesignationService;
import com.ems.employeemanagementsystem.service.EmployeeService;
import com.ems.employeemanagementsystem.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {

private SalaryRepositoy salaryRepositoy;
private SalaryService salaryService;
private DepartmentService departmentService;
private DesignationService designationService;
private EmployeeService employeeService;
@Autowired

    public SalaryController(SalaryRepositoy salaryRepositoy, SalaryService salaryService, DepartmentService departmentService, DesignationService designationService, EmployeeService employeeService) {
        this.salaryRepositoy = salaryRepositoy;
        this.salaryService = salaryService;
        this.departmentService = departmentService;
        this.designationService = designationService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = ("/search"), method = RequestMethod.GET)
    public String getSalaryByEmployee(@ModelAttribute("salary") SalaryModel salaryModel, Model model,
                                    @RequestParam(value = "query", defaultValue = "", required = false) String query) {
        List<Salaries> salaryModels = salaryService.GetAllSalaries(query);


        model.addAttribute("salaryList", salaryModels);

        return "Salary/salary";

    }


    @GetMapping("/salaries")
    public String salaryList(Model model){
    List<SalaryModel> salaries=new ArrayList<>();
    for(SalarListProjection salarListProjection:salaryRepositoy.salaryList())
    {
        SalaryModel salaryModel=new SalaryModel();
        salaryModel.setId(salarListProjection.getId());
        salaryModel.setName(salarListProjection.getName());
        salaryModel.setEmployeeId(salarListProjection.getEmployeeId());
        salaryModel.setDesignation(salarListProjection.getDesignation());
        salaryModel.setDepartment(salarListProjection.getDeptName());
        salaryModel.setBasic(salarListProjection.getBasic());
        salaryModel.setBonus(salarListProjection.getBonus());
        salaryModel.setTotal(salarListProjection.getTotal());
        salaryModel.setYear(salarListProjection.getYear());
        salaries.add(salaryModel);
    }
    model.addAttribute("salaryList",salaries);

    return "Salary/salary";
}

//@RequestMapping(value = ("/result"),method = RequestMethod.POST)
//public String result(@ModelAttribute SalaryModel salary, BindingResult result,Model model)
//{
//    model.addAttribute("salaryList",salary);
//    return "Salary/salary";
//}



@GetMapping("/addsalary")
    public String AddsalaryList(Model model){
        List<Salaries> salaryList=salaryService.ListAll();
        List<Department>departmentList=departmentService.ListAll();
        List<Designations> designationsList =designationService.ListAll();
        List<Employee>employeeList=employeeService.GetAllEployee();
        model.addAttribute("salaryList",salaryList);
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("designationList", designationsList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("salary",new AddSalaryModel());
        return "Salary/addSalary";
}

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveList(@ModelAttribute("salary") AddSalaryModel addSalaryModel,BindingResult result) {
        salaryService.saveList(addSalaryModel);
        return "redirect:/salary/salaries";
}


    @RequestMapping(value = "/edit/{id}")
    public ModelAndView updateSalaryTable(@PathVariable("id") long id,
                                    @ModelAttribute AddSalaryModel addSalaryModel){

        ModelAndView modelAndView=new ModelAndView("Salary/addSalary");
        List<Department>departmentList=departmentService.ListAll();
        List<Designations> designationsList =designationService.ListAll();
        List<Employee>employeeList=employeeService.GetAllEployee();
        AddSalaryModel addSalaryModel1 =salaryService.updateSalaryTable(id);
        modelAndView.addObject("salary", addSalaryModel1);
        modelAndView.addObject("departmentList",departmentList);
        modelAndView.addObject("designationList", designationsList);
        modelAndView.addObject("employeeList",employeeList);

        return modelAndView;



}

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") long id)
    {
        salaryService.delete(id);
        return "redirect:/salary/salaries";
    }

    @GetMapping("/getAllEmployeeListWithSalary")
    public ResponseEntity<Page<SalarListProjection>> getAllEmployeeListWithSalary(@RequestParam(required = false) Optional<String> search, Optional<Integer> pageNo, Optional<Integer> pageSize){
    return ResponseEntity.ok(salaryService.getAllEmployeeListWithSalary(search.orElse("_"), pageNo.orElse(0), pageSize.orElse(2)));
    }
}
