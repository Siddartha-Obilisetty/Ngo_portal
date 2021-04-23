package com.capgemini.model;

import javax.persistence.*;

@Entity
public class Employee 
{
	@Id
	private int empid;
	
	private String ename;
	
	private String email;
	
	private String phone;
	
	private String username;
	
	private String password;
	
	@OneToOne
	@JoinColumn(name = "add_Id")
	private Address address;
	
	public Employee() {}
	
	public Employee(int empid, String ename, String email, String phone, String username, String password,Address address) {
		super();
		this.empid = empid;
		this.ename = ename;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.address=address;
	}

	
	public int getEmpid() {
		return empid;
	}


	public void setEmpid(int empid) {
		this.empid = empid;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
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

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", ename=" + ename + ", email=" + email + ", phone=" + phone + ", username="
				+ username + ", password=" + password + ", address=" + address + "]";
	}
	
	

}
