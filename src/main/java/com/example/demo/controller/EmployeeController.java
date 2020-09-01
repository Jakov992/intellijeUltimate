package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        System.out.println("Gettamo: " + employeeId);
        return "save_employee";
    }

    @GetMapping("/showFormDelete")
    public String showFormDelete(@RequestParam(name = "id") Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        System.out.println("Gettamo: " + employeeId);
        return "delete_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Successfully saved!");
        return "redirect:/";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        // todo: važne napomene
        // Model ne šalje th:object="${employee}", nego ono što se nalazi u inputu!!!!!!!!!!!
        // Dosta je poslati id i ovaj se može izbrisati(jer deleteEmployee() je dosta id za izbrisat)
        employeeService.deleteEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Successfully deleted!");
        return "redirect:/";
    }
}
