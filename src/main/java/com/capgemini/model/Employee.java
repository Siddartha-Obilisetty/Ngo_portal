package com.capgemini.model;

//imports

import javax.persistence.*;

//Entity class

@Entity
public class Employee 
{
	@Id
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	private String email;
	
	private String phone;
	
	private String username;
	
	private String password;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public Employee() {}	//no parameter constructor
	
	//Parameterized constructor
	
	public Employee(int employeeId, String employeeName, String email, String phone, String username, String password,
			Address address) {
		this.employeeId = employeeId;	this.employeeName = employeeName;
		this.email = email;				this.phone = phone;
		this.username = username;		this.password = password;
		this.address = address;
	}

	//Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	//ToString
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", phone="
				+ phone + ", username=" + username + ", password=" + password + ", address=" + address + "]";
	}

}
