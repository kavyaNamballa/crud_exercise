package com.exercice.crud.controller;

import com.exercice.crud.model.Student;
import com.exercice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @Autowired
    private StudentService service;

    @GetMapping("/viewStudents")
    public String viewAllStudents(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("list", service.getAllStudents());
        model.addAttribute("message", message);
        return "viewStudents"; // Return the view name directly
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("std", new Student());
        return "addStudent"; // Return the view name directly
    }

    @PostMapping("/saveStudent")
    public String saveStudent(Student std, RedirectAttributes redirectAttributes) {
        if(service.saveOrUpdateStudent(std)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Save Failure");
        }
        return "redirect:/viewStudents"; // Use correct URL for redirect
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("std", service.getStudentById(id));
        return "editStudent"; // Return the view name directly
    }

    @PostMapping("/editStudent")
    public String editStudent(Student std, RedirectAttributes redirectAttributes) {
        if(service.saveOrUpdateStudent(std)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Edit Failure");
        }
        return "redirect:/viewStudents"; // Use correct URL for redirect
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.deleteStudent(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Delete Failure");
        }
        return "redirect:/viewStudents"; // Use correct URL for redirect
    }
}
