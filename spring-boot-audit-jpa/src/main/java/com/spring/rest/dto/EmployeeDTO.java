package com.spring.rest.dto;

public class EmployeeDTO {

	private Long employeeId;

	private String employeeName;

	private String employeeEmail;

	private String employeeGender;

	private int employeePhone = 1234567891;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
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

}
