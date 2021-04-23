package com.capgemini.model;

import javax.persistence.*;

@Entity
public class Admin 
{
	@Id
	private int adminid;
	
	private String admin_username;
	
	private String admin_password;
	
	
	public Admin() {}
	
	public Admin(int adminid, String admin_username, String admin_password) {
		super();
		this.adminid = adminid;
		this.admin_username = admin_username;
		this.admin_password = admin_password;
	}

	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	
	
}
