package com.capgemini.model;

import javax.persistence.*;

@Entity
public class DonationBox 
{
	@Id
	@Column(name="ngo_name")
	private String ngoName;
	
	@Column(name="reg_no")
	private String registrationNumber;
	
	@Column(name="acc_no")
	private String accountNumber;
	
	@Column(name="total_collection")
	private double totalCollection;
	
	public DonationBox() {}
	
	public DonationBox(String ngoName, String registrationNumber, String accountNumber, double totalCollection) {
		this.ngoName = ngoName;
		this.registrationNumber = registrationNumber;
		this.accountNumber = accountNumber;
		this.totalCollection = totalCollection;
	}
	public String getNgoName() {
		return ngoName;
	}
	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(double totalCollection) {
		this.totalCollection = totalCollection;
	}
	
}
