package com.libraryManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String rollNo;
	
	@Column(unique = true, nullable = false )
	private String email;
	
	
	private String department;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@JsonBackReference(value = "student-issue")
	private List<IssueRecord> issueRecords;

	public Student() {
		super();
	}

	public Student(Long id, String name, String rollNo, String email, String department,
			List<IssueRecord> issueRecords) {
		super();
		this.id = id;
		this.name = name;
		this.rollNo = rollNo;
		this.email = email;
		this.department = department;
		this.issueRecords = issueRecords;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollno) {
		this.rollNo = rollno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<IssueRecord> getIssueRecords() {
		return issueRecords;
	}

	public void setIssueRecords(List<IssueRecord> issueRecords) {
		this.issueRecords = issueRecords;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", rollno=" + rollNo + ", email=" + email + ", department="
				+ department + ", issueRecords=" + issueRecords + "]";
	}
	
	
	
}
