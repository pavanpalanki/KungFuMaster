package com.dbms.kungfu.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.kungfu.entity.AccountSummary;
import com.dbms.kungfu.entity.AttendanceAttributes;
import com.dbms.kungfu.entity.FinanceAttributes;
import com.dbms.kungfu.entity.ProgressAttributes;
import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.StudentAttendance;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service
public interface StudentService {

	List<Student> getStudents();

	Student saveStudent(Student theStudent, int rank, String level);

	String recordAttendance(int studentId);

	List<Student> searchStudent(String searchString);

	Student getStudentDetails(int studentId);

	String addFinance(int studentId, int serviceCatalogueId);

	List<FinanceAttributes> getStudentAccountSummary(Student theStudent);

	List<AttendanceAttributes> getStudentAttendanceSummary(Student theStudent);

	List<ProgressAttributes> getStudentProgressSummary(Student theStudent);

	String awardBelt(int studentId, int progressId) throws MySQLIntegrityConstraintViolationException;

	List<FinanceAttributes> searchFinanceRange(Student theStudent, Date fromDate, Date toDate);

	Boolean verifyAge(String dob);

}
