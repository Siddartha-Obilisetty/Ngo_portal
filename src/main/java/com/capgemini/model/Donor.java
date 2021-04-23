package com.capgemini.model;

import javax.persistence.*;

@Entity
public class Donor 
{
	@Id
	private int donor_id;
	
	private String donor_name;
	
	private String email;
	
	private String phone;
	
	private String username;
	
	private String password;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
		
	public Donor() {}

	public Donor(int donor_id, String donor_name, String email, String phone, String username, String password,Address address) {
		super();
		this.donor_id = donor_id;
		this.donor_name = donor_name;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.address=address;
	}

	public int getDonor_id() {
		return donor_id;
	}

	public void setDonor_id(int donor_id) {
		this.donor_id = donor_id;
	}

	public String getDonor_name() {
		return donor_name;
	}

	public void setDonor_name(String donor_name) {
		this.donor_name = donor_name;
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
	
	

}
