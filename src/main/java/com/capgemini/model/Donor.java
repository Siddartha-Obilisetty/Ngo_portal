package com.capgemini.model;

//imports

import javax.persistence.*;

//Entity Class

@Entity
public class Donor 
{
	@Id
	@Column(name = "donor_id")
	private int donorId;
	
	@Column(name = "donor_name")
	private String donorName;
	
	private String email;
	
	private String phone;
	
	private String username;
	
	private String password;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
		
	public Donor() {}	//no parameter constructor

	//Parameterized constructor
	public Donor(int donorId, String donorName, String email, String phone, String username, String password,
			Address address) {
		this.donorId = donorId;		this.donorName = donorName;
		this.email = email;			this.phone = phone;
		this.username = username;	this.password = password;
		this.address=address;
	}

	//Getters and Setters
	
	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
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
		return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", email=" + email + ", phone=" + phone
				+ ", username=" + username + ", password=" + password + ", address=" + address + "]";
	}

}
