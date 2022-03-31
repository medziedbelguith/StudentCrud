package com.example.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import com.example.emp.domain.Student;
import com.example.emp.repository.StudentRepository;
import com.example.emp.service.StudentService;
 
@Controller
public class StudentController {
	
	 @Autowired
	    private StudentService service;
	    private StudentRepository repo;
 
	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        List<Student> liststudent = service.listAll();
	        model.addAttribute("liststudent", liststudent);
	        System.out.print("Get / ");	
	        return "index";
	    }
 
	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("student", new Student());
	        System.out.print("Get / ");	
	        return "new";
	    }
 
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveStudent(@ModelAttribute("student") Student std) {
	        service.save(std);
	        return "redirect:/";
	    }
 
	    @RequestMapping("/edit/{id}")
	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("new");
	        Student std = service.get(id);
	        mav.addObject("student", std);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{id}")
	    public String deletestudent(@PathVariable(name = "id") int id) {
	        service.delete(id);
	        return "redirect:/";
	    }
	    @RequestMapping(value = "/students")
	    @CrossOrigin(origins = "http://localhost:4200")
	    public ResponseEntity<Object> getStudents() {
	       return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
	    }
}

