package com.spring.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spring.rest.audit.Auditable;

@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeEntity extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long employeeId;

	@Column(name = "emp_name")
	private String employeeName;

	@Column(name = "emp_email")
	private String employeeEmail;

	@Size(max = 1, message = "Gender must be 1 char..")
	@Column(name = "emp_gender")
	private String employeeGender;

	@NotNull
	@Column(name = "emp_phone")
	private Integer employeePhone;

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

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeGender=" + employeeGender + ", employeePhone=" + employeePhone + "]";
	}

}
