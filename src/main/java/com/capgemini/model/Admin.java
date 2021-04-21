package com.capgemini.model;

import javax.persistence.*;

@Entity
public class Admin 
{
	@Id
	@Column(name="admin_id")
	private int adminid;
	
	@Column(name="admin_username")
	private String adminUsername;
	
	@Column(name="admin_password")
	private String adminPassword;
	
	
	public Admin() {}
	public Admin(int adminid, String adminUsername, String adminPassword) {
		this.adminid = adminid;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
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
	
}
