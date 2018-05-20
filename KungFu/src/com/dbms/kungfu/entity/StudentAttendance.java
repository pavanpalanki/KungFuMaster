package com.dbms.kungfu.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_attendance")
public class StudentAttendance {
	
	@Id
	@Column(name="stu_attendance_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="stu_id")
	private Student student;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="time_table_id")
	private TimeTable timeTable;
	
	@Column(name="date_attended")
	private Date dateAttended;

	public StudentAttendance(){
		
	}

	public StudentAttendance(Date dateAttended) {
		super();
		this.dateAttended = dateAttended;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	public Date getDateAttended() {
		return dateAttended;
	}

	public void setDateAttended(Date dateAttended) {
		this.dateAttended = dateAttended;
	}

	@Override
	public String toString() {
		return "StudentAttendance [id=" + id + ", dateAttended=" + dateAttended + "]";
	}
	
		
}
