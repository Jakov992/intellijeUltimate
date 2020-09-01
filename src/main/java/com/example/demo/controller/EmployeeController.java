package com.example.demo.controller;

import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Qualifier("service1")
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/getAddNewEmployeeForm")
    public String getAddNewEmployeeForm(Model model) {
        model.addAttribute("employee", employeeService.getNewEmployee());
        System.out.println("ssssssssssssss");
        return "save_employee";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormUpdate(@RequestParam(name = "id") Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "save_employee";
    }

    @GetMapping("/showFormDelete")
    public String showFormDelete(@RequestParam(name = "id") Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "delete_employee";
    }
}
