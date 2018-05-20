package com.dbms.kungfu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="service_catalogue")
public class ServiceCatalogue {
	
	@Id
	@Column(name="service_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="category")
	private String category;
	
	@Column(name="sub_category")
	private String subCategory;
	
	@Column(name="fees")
	private long fees;
	
	@Column(name="description")
	private String description;
	
	
	public ServiceCatalogue(){
		
	}
	
	public ServiceCatalogue(String category, String subCategory, long fees, String description) {
		super();
		this.category = category;
		this.subCategory = subCategory;
		this.fees = fees;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	@Override
	public String toString() {
		return "ServiceCatalogue [id=" + id + ", category=" + category + ", subCategory=" + subCategory + ", fees="
				+ fees + ", description=" + description + "]";
	}
	
}
