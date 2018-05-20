package com.dbms.kungfu.entity;

import java.sql.Date;

public class FinanceAttributes {
	
	private String category;
	
	private String subCategory;
	
	private long fees;
	
	private Date datePaid;
	
	public FinanceAttributes(){
		
	}

	public FinanceAttributes(String category, String subCategory, long fees, Date datePaid) {
		super();
		this.category = category;
		this.subCategory = subCategory;
		this.fees = fees;
		this.datePaid = datePaid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public long getFees() {
		return fees;
	}

	public void setFees(long fees) {
		this.fees = fees;
	}

	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
	
}
