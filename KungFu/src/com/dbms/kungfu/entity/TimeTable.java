package com.dbms.kungfu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="time_table")
public class TimeTable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="level")
	private String level;
	
	@Column(name="rank")
	private String rank;
	
	@Column(name="day")
	private String day;
	
	@Column(name="time")
	private String time;
	
	@OneToMany(mappedBy="timeTable", 
			cascade={CascadeType.ALL})
	private List<StudentAttendance> studentAttendance;
	
	public void addStudentAttendance(StudentAttendance theStudentAttendance){
		if(studentAttendance==null){
			studentAttendance=new ArrayList<>();
		}
		studentAttendance.add(theStudentAttendance);
		theStudentAttendance.setTimeTable(this);
	}
	
	public TimeTable(){
		
	}

	public TimeTable(String level, String rank, String day, String time) {
		super();
		this.level = level;
		this.rank = rank;
		this.day = day;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<StudentAttendance> getStudentAttendance() {
		return studentAttendance;
	}

	public void setStudentAttendance(List<StudentAttendance> studentAttendance) {
		this.studentAttendance = studentAttendance;
	}

	@Override
	public String toString() {
		return "TimeTable [id=" + id + ", level=" + level + ", rank=" + rank + ", day=" + day + ", time=" + time + "]";
	}
	
		
}
