package com.capgemini.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Donor 
{
	@Id
	@Column(name="donor_id")
	private int donorId;
	
	@Column(name="donor_name")
	private String donorName;
	
	@Column(name="donor_email")
	private String donorEmail;
	
	@Column(name="donor_phone")
	private String donorPhone;
	
	@Column(name="donor_username")
	private String donorUsername;
	
	@Column(name="donor_password")
	private String donorPassword;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	
	@OneToMany(mappedBy = "donor",cascade = CascadeType.ALL)
	private List<Donation> donations;
	
	public Donor() {}
	
	public Donor(int donorId, String donorName, String donorEmail, String donorPhone, String donorUsername,
			String donorPassword, Address address) {
		this.donorId = donorId;
		this.donorName = donorName;
		this.donorEmail = donorEmail;
		this.donorPhone = donorPhone;
		this.donorUsername = donorUsername;
		this.donorPassword = donorPassword;
		this.address = address;
	}
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
	public String getDonorEmail() {
		return donorEmail;
	}
	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}
	public String getDonorPhone() {
		return donorPhone;
	}
	public void setDonorPhone(String donorPhone) {
		this.donorPhone = donorPhone;
	}
	public String getDonorUsername() {
		return donorUsername;
	}
	public void setDonorUsername(String donorUsername) {
		this.donorUsername = donorUsername;
	}
	public String getDonorPassword() {
		return donorPassword;
	}
	public void setDonorPassword(String donorPassword) {
		this.donorPassword = donorPassword;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
