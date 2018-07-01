package com.dbms.kungfu.dao;

import java.sql.Date;
import java.util.List;

import com.dbms.kungfu.entity.AccountSummary;
import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.StudentAttendance;
import com.dbms.kungfu.entity.TimeTable;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface StudentDao {

	List<Student> getStudents();

	Student saveStudent(Student theStudent, int rank, String level);

	List<TimeTable> getTodayTimeTable(String today);

	void recordAttendance(int studentId, TimeTable schedule);

	List<Student> searchStudent(String searchString);

	Boolean checkDuplicateAttendance(int studentId, int id);

	Student getStudentDetails(int studentId);

	String addFinance(int studentId, int serviceCatalogueId);

	List<AccountSummary> getStudentAccountSummary(Student theStudent);

	List<StudentAttendance> getStudentAttendanceSummary(Student theStudent);

	String awardBelt(int studentId, int progressId) throws MySQLIntegrityConstraintViolationException;

	List<AccountSummary> searchFinanceRange(Student theStudent, Date fromDate, Date toDate);

}
