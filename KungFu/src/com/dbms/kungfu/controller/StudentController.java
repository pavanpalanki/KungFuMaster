package com.dbms.kungfu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@GetMapping("list")
	public String listStudents(Model theModel){
				
		return "list-students";
	}

}
