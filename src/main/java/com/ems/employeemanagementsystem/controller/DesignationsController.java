package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.entity.Designations;
import com.ems.employeemanagementsystem.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/designation")
public class DesignationsController {
    @Autowired
    private DesignationService designationService;



    @GetMapping("/designation")
public String designationList(Model model){
        List<Designations> designationsList =designationService.ListAll();
        model.addAttribute("title","designation");
        model.addAttribute("designationList", designationsList);
        return "/designation";
    }

@GetMapping("/addDesignation")
    public String AddNewDesignation(Model model){
        List<Designations> designationsList =designationService.ListAll();
        model.addAttribute("designationList", designationsList);
        model.addAttribute("designation",new Designations());
        return "addDesignation";
}
@RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveDesignation(@ModelAttribute("designation") Designations designations){
        designationService.saveDesignation(designations);
    return "redirect:designation";
}

@RequestMapping(value = ("/edit/{id}"))
    public ModelAndView edit(@PathVariable("id") long id)
{
    ModelAndView modelAndView=new ModelAndView("addDesignation");
    Designations designations =designationService.edit(id);
    modelAndView.addObject("designation", designations);
    return modelAndView;
}

@RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        designationService.delete(id);
        return "redirect:/designation/designation";
}
}
