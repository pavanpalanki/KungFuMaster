package com.dbms.kungfu.dao;

import java.util.List;

import com.dbms.kungfu.entity.Student;
import com.dbms.kungfu.entity.TimeTable;

public interface StudentDao {

	List<Student> getStudents();

	void saveStudent(Student theStudent, int rank, String level);

	List<TimeTable> getTodayTimeTable(String today);

	void recordAttendance(int studentId, TimeTable schedule);

	List<Student> searchStudent(String searchString);

	Boolean checkDuplicateAttendance(int studentId, int id);

	Student getStudentDetails(int studentId);

	String addFinance(int studentId, int serviceCatalogueId);

}
