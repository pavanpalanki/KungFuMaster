package com.dbms.kungfu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;

@Service
public interface StudentService {

	List<Student> getStudents();

	void saveStudent(Student theStudent, int rank, String level);

	String recordAttendance(int studentId);

	List<Student> searchStudent(String searchString);

	Student getStudentDetails(int studentId);

	String addFinance(int studentId, int serviceCatalogueId);

}
