package com.dbms.kungfu.entity;

import java.sql.Date;
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
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="stu_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="stu_first_name")
	private String firstName;
	
	@Column(name="stu_last_name")
	private String lastName;
	
	@Column(name="stu_dob")
	private String dob;
	
	@Column(name="stu_join_date")
	private Date joinDate;
	
	@Column(name="stu_mobile")
	private String mobile;
	
	@Column(name="stu_email")
	private String email;
	
	@Column(name="stu_door_no")
	private int doorNo;
	
	@Column(name="stu_street")
	private String street;
	
	@Column(name="stu_city")
	private String city;
	
	@Column(name="stu_province")
	private String province;
	
	@Column(name="stu_postal_code")
	private String postalCode;
	
	@Column(name="stu_primary_contact")
	private String primaryContact;
	
	@Column(name="stu_primary_contact_mobile")
	private String primaryContactMobile;
	
	@Column(name="stu_primary_contact_email")
	private String primaryContactEmail;
	
	@Column(name="stu_secondary_contact")
	private String secondaryContact;
	
	@Column(name="stu_secondary_contact_mobile")
	private String secondaryContactMobile;
	
	@Column(name="stu_secondary_contact_email")
	private String secondaryContactEmail;
	
	@OneToMany(mappedBy="student", 
			cascade={CascadeType.DETACH,CascadeType.MERGE,
					CascadeType.PERSIST,CascadeType.REFRESH})
	private List<StudentProgress> studentProgress;
	
	public void addStudentProgress(StudentProgress theStudentProgress){
		if(studentProgress==null){
			studentProgress=new ArrayList<>();
		}
		studentProgress.add(theStudentProgress);
		theStudentProgress.setStudent(this);
	}
	
	@OneToMany(mappedBy="student", 
			cascade={CascadeType.ALL})
	private List<StudentAttendance> studentAttendance;
	
	public void addStudentAttendance(StudentAttendance theStudentAttendance){
		if(studentAttendance==null){
			studentAttendance=new ArrayList<>();
		}
		studentAttendance.add(theStudentAttendance);
		theStudentAttendance.setStudent(this);
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="studentInfo", 
			cascade={CascadeType.ALL})
	private List<AccountSummary> accountSummary;
	
	public void addAccountSummary(AccountSummary theAccountSummary){
		if(accountSummary==null){
			accountSummary=new ArrayList<>();
		}
		accountSummary.add(theAccountSummary);
		theAccountSummary.setStudentInfo(this);
	}
	
	public Student(){
		
	}

	public Student(String firstName, String lastName, String dob, Date joinDate, String mobile, String email,
			int doorNo, String street, String city, String province, String postalCode, String primaryContact,
			String primaryContactMobile, String primaryContactEmail, String secondaryContact,
			String secondaryContactMobile, String secondaryContactEmail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.joinDate = joinDate;
		this.mobile = mobile;
		this.email = email;
		this.doorNo = doorNo;
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.primaryContact = primaryContact;
		this.primaryContactMobile = primaryContactMobile;
		this.primaryContactEmail = primaryContactEmail;
		this.secondaryContact = secondaryContact;
		this.secondaryContactMobile = secondaryContactMobile;
		this.secondaryContactEmail = secondaryContactEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public String getPrimaryContactMobile() {
		return primaryContactMobile;
	}

	public void setPrimaryContactMobile(String primaryContactMobile) {
		this.primaryContactMobile = primaryContactMobile;
	}

	public String getPrimaryContactEmail() {
		return primaryContactEmail;
	}

	public void setPrimaryContactEmail(String primaryContactEmail) {
		this.primaryContactEmail = primaryContactEmail;
	}

	public String getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(String secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public String getSecondaryContactMobile() {
		return secondaryContactMobile;
	}

	public void setSecondaryContactMobile(String secondaryContactMobile) {
		this.secondaryContactMobile = secondaryContactMobile;
	}

	public String getSecondaryContactEmail() {
		return secondaryContactEmail;
	}

	public void setSecondaryContactEmail(String secondaryContactEmail) {
		this.secondaryContactEmail = secondaryContactEmail;
	}
	
	

	public List<StudentProgress> getStudentProgress() {
		return studentProgress;
	}

	public void setStudentProgress(List<StudentProgress> studentProgress) {
		this.studentProgress = studentProgress;
	}
	
	
	public List<StudentAttendance> getStudentAttendance() {
		return studentAttendance;
	}

	public void setStudentAttendance(List<StudentAttendance> studentAttendance) {
		this.studentAttendance = studentAttendance;
	}
	
	
	public List<AccountSummary> getAccountSummary() {
		return accountSummary;
	}

	public void setAccountSummary(List<AccountSummary> accountSummary) {
		this.accountSummary = accountSummary;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", joinDate=" + joinDate + ", mobile=" + mobile + ", email=" + email + ", doorNo=" + doorNo
				+ ", street=" + street + ", city=" + city + ", province=" + province + ", postalCode=" + postalCode
				+ ", primaryContact=" + primaryContact + ", primaryContactMobile=" + primaryContactMobile
				+ ", primaryContactEmail=" + primaryContactEmail + ", secondaryContact=" + secondaryContact
				+ ", secondaryContactMobile=" + secondaryContactMobile + ", secondaryContactEmail="
				+ secondaryContactEmail + "]";
	}
	
}
