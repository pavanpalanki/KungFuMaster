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
@Table(name="account_summary")
public class AccountSummary {

	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	
	@ManyToOne
	@JoinColumn(name="stu_id")
	private Student studentInfo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="service_id")
	private ServiceCatalogue serviceCatalogue;
	
	@Column(name="date_paid")
	private Date datePaid;
	
	public AccountSummary() {
		
	}

	public AccountSummary(Date datePaid) {
		super();
		this.datePaid = datePaid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
	
	

	public Student getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(Student studentInfo) {
		this.studentInfo = studentInfo;
	}

	public ServiceCatalogue getServiceCatalogue() {
		return serviceCatalogue;
	}

	public void setServiceCatalogue(ServiceCatalogue serviceCatalogue) {
		this.serviceCatalogue = serviceCatalogue;
	}

	@Override
	public String toString() {
		return "AccountSummary [id=" + id + ", datePaid=" + datePaid + "]";
	}
		
}
