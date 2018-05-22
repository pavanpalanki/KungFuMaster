package com.dbms.kungfu.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbms.kungfu.entity.AccountSummary;
import com.dbms.kungfu.entity.AttendanceAttributes;
import com.dbms.kungfu.entity.FinanceAttributes;
import com.dbms.kungfu.entity.ProgressAttributes;
import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.StudentAttendance;
import com.dbms.kungfu.entity.StudentProgress;
import com.dbms.kungfu.service.FinanceService;
import com.dbms.kungfu.service.StudentService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/student")
@Component
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private FinanceService financeService;
	
	@Value("#{ranks}")
	private Map<String, String> ranks;
	
	@GetMapping("list")
	public String listStudents(Model theModel){
				
		List<Student> students=studentService.getStudents();
		
		theModel.addAttribute("students",students);
		return "list-students";
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model theModel){		
		
		Student theStudent=new Student();
		
		theModel.addAttribute("ranks",ranks);
		
		theModel.addAttribute("theStudent",theStudent);
		
		return "student-form";
	}
	
	@GetMapping("/showStudentDetails")
	public String showStudentDetails(@ModelAttribute("studentId") int studentId,
			Model theModel){		
		
		Student theStudent = studentService.getStudentDetails(studentId);

		theModel.addAttribute("theStudent",theStudent);		
		
		return "student-details";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("theStudent") Student theStudent, 
			HttpServletRequest request, BindingResult theBindingResult){
		
		String level= request.getParameter("level");
		
		int rank = Integer.parseInt(request.getParameter("rank"));
		
		studentService.saveStudent(theStudent,rank,level);
		
		if (theBindingResult.hasErrors()) {
			return "student-form";
		}
		else {
			return "redirect:/student/list";
		}
	}
	
	@GetMapping("/recordAttendance")
	public String recordAttendance(@ModelAttribute("studentId") int studentId,
			Model theModel){
		
		Student theStudent=new Student();
		
		theStudent.setId(studentId);
		
		String result = studentService.recordAttendance(studentId);
		
		theModel.addAttribute("result", result);
		
		return "redirect:/student/list?result="+result;				
		
	}
	
	@PostMapping("/searchStudent")
	public String searchStudent(@ModelAttribute("theSearchString") String searchString,
			Model theModel){
		
		List<Student> students=studentService.searchStudent(searchString);
		
		theModel.addAttribute("students",students);
		
		return "list-students";			
		
	}
	
	@GetMapping("/showFinanceForm")
	public String showFinance(@ModelAttribute("studentId") int studentId,
			Model theModel){
		

		List<ServiceCatalogue> serviceCatalogue = financeService.getServiceCatalogue();
		
		Student student=studentService.getStudentDetails(studentId);
		
		theModel.addAttribute("services", serviceCatalogue);
		
		theModel.addAttribute("student", student);
		
		return "finance-form";				
		
	}	
	
	@GetMapping("/showAchievementForm")
	public String showAchievementForm(@ModelAttribute("studentId") int studentId
				,Model theModel){
		
		Student theStudent = studentService.getStudentDetails(studentId);

		List<ProgressAttributes> theStudentProgress = studentService.getStudentProgressSummary(theStudent);
		
		theModel.addAttribute("theStudentProgress", theStudentProgress);
				
		return "student-progress-form";				
		
	}
	
	@PostMapping("/awardBelt")
	public String awardBelt(@ModelAttribute("progressId") int progressId
			 ,@ModelAttribute("studentId") int studentId, Model theModel) throws MySQLIntegrityConstraintViolationException{
		

		String result = studentService.awardBelt(studentId,progressId);
		
		return "redirect:/student/list?result="+result;				
		
	}
	
	@GetMapping("/addFinance")
	public String addFinance(@ModelAttribute("studentId") int studentId,
			@ModelAttribute("serviceId") int serviceCatalogueId, Model theModel){
		

		String result = studentService.addFinance(studentId,serviceCatalogueId);
		
		return "redirect:/student/list?result="+result;				
		
	}
	
	@GetMapping("/getStudentFinances")
	public String getStudentFinances(@ModelAttribute("studentId") int studentId,
			Model theModel) {

		Student theStudent = studentService.getStudentDetails(studentId);

		List<FinanceAttributes> finances = studentService.getStudentAccountSummary(theStudent);

		theModel.addAttribute("theAccountSummary", finances);

		return "student-finances";

	}
	
	@PostMapping("searchFinanceRange")
	public String searchFinanceRange(@ModelAttribute("studentId") int studentId,
			@ModelAttribute("fromDate") Date fromDate,
			@ModelAttribute("toDate") Date toDate,
			Model theModel) {

		Student theStudent = studentService.getStudentDetails(studentId);		

		List<FinanceAttributes> finances = studentService.searchFinanceRange(theStudent, fromDate, toDate);

		theModel.addAttribute("theAccountSummary", finances);

		return "student-finances";

	}
	
	
	@GetMapping("/getStudentAttendance")
	public String getStudentAttendance(@ModelAttribute("studentId") int studentId,
			Model theModel) {

		Student theStudent = studentService.getStudentDetails(studentId);		

		List<AttendanceAttributes> attendance = studentService.getStudentAttendanceSummary(theStudent);
		
		theModel.addAttribute("theStudentAttendance", attendance);

		return "student-attendance";

	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

}
