package com.spring.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "employee")
//@EntityListeners(AuditingEntityListener.class)
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empl_seq")
	@GenericGenerator(name = "empl_seq", strategy = "com.spring.rest.entity.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "10"),
			@Parameter(name= StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%09d")})
//	private Long employeeId;

	private String employeeId;

	@NotEmpty(message = "Please provide a name")
	@Column(name = "emp_name")
	private String employeeName;

	@Column(name = "emp_email")
	private String employeeEmail;

	@Max(value = 1, message = "astatus flug field must be length '{value}'")
	@Column(name = "emp_gender")
	private String employeeGender;

	@Column(name = "emp_phone")
	private Integer employeePhone;

	@Column(name = "file_upload")
	private String fileUpload;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public int getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(int employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public void setEmployeePhone(Integer employeePhone) {
		this.employeePhone = employeePhone;
	}

}
