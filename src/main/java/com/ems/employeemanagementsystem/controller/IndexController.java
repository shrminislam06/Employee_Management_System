package com.ems.employeemanagementsystem.controller;

import com.ems.employeemanagementsystem.repository.SalaryRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

 @Autowired
    private SalaryRepositoy salaryRepositoy;

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("tittle","home page");
        return "index";
    }



    }



