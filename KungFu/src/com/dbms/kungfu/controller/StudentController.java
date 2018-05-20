package com.dbms.kungfu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbms.kungfu.entity.AccountSummary;
import com.dbms.kungfu.entity.AttendanceAttributes;
import com.dbms.kungfu.entity.FinanceAttributes;
import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.StudentAttendance;
import com.dbms.kungfu.service.FinanceService;
import com.dbms.kungfu.service.StudentService;

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
		
		List<AccountSummary> theAccountSummary = theStudent.getAccountSummary();
		
		//List<StudentAttendance> theStudentAttendance = theStudent.getStudentAttendance();
		
		List<FinanceAttributes> finances = new ArrayList<>();
		
		List<AttendanceAttributes> attendance = new ArrayList<>();
		
		for(AccountSummary acc : theAccountSummary){
			
			FinanceAttributes tmpFin = new FinanceAttributes();
			tmpFin.setCategory(acc.getServiceCatalogue().getCategory());
			tmpFin.setSubCategory(acc.getServiceCatalogue().getSubCategory());
			tmpFin.setFees(acc.getServiceCatalogue().getFees());
			tmpFin.setDatePaid(acc.getDatePaid());
			finances.add(tmpFin);
		
		}
		
		/*for(StudentAttendance attend : theStudentAttendance){
			
			AttendanceAttributes tmpAtt = new AttendanceAttributes();
			tmpAtt.setLevel(attend.getTimeTable().getLevel());
			tmpAtt.setRank(attend.getTimeTable().getRank());
			tmpAtt.setLevel(attend.getTimeTable().getLevel());
			tmpAtt.setTime(attend.getTimeTable().getTime());
			tmpAtt.setDateAttended(attend.getDateAttended());
			
		}*/
		
		theModel.addAttribute("theStudent",theStudent);
		theModel.addAttribute("theAccountSummary",finances);
		//theModel.addAttribute("theStudentAttendance",attendance);
		
		return "student-details";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("theStudent") Student theStudent, 
			HttpServletRequest request){
		
		String level= request.getParameter("level");
		
		int rank = Integer.parseInt(request.getParameter("rank"));
		
		studentService.saveStudent(theStudent,rank,level);
		
		return "redirect:/student/list";
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
	
	@GetMapping("/addFinance")
	public String addFinance(@ModelAttribute("studentId") int studentId,
			@ModelAttribute("serviceId") int serviceCatalogueId, Model theModel){
		

		String result = studentService.addFinance(studentId,serviceCatalogueId);
		
		return "redirect:/student/list?result="+result;				
		
	}

}
