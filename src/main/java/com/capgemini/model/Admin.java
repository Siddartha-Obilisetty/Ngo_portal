package com.capgemini.model;

//imports

import javax.persistence.*;

//Entity Class

@Entity
public class Admin 
{
	@Id
	@Column(name = "admin_id")
	private int adminId;
	
	@Column(name = "admin_username")
	private String adminUsername;
	
	@Column(name = "admin_password")
	private String adminPassword;
	
	
	public Admin() {}	//no parameter constructor
	
	//Parameterized constructor
	public Admin(int adminId, String adminUsername, String adminPassword) {
		this.adminId = adminId;				this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}

	//Getters and Setters
	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	public String getAdminUsername() {
		return adminUsername;
	}


	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}


	public String getAdminPassword() {
		return adminPassword;
	}


	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	//ToString
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
				+ "]";
	}
	
	
}
