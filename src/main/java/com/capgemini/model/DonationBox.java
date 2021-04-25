package com.capgemini.model;

//imports

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

//Entity Class

@Entity
@Table(name = "donation_box")
public class DonationBox 
{
	@Id
	@Column(name = "ngo_name")
	private String ngoName;
	
	@Column(name = "registration_no")
	private String registrationNo;
	
	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="total_money_collection")
	private double totalMoneyCollection;
	
	@Column(name="total_books_collection")
	private double totalBooksCollection;
	
	@Column(name="total_clothes_collection")
	private double totalClothesCollection;
	
	@Column(name="total_edible_collection")
	private double totalEdibleCollection;
	
	@Column(name="total_other_collection")
	private double totalOtherCollection;
	
	
	public DonationBox() {}	//no parameter constructor

	//Parameterized constructor
	public DonationBox(String ngoName, String registrationNo, String accountNo, double totalMoneyCollection,
			double totalBooksCollection, double totalClothesCollection, double totalEdibleCollection,
			double totalOtherCollection) {
		this.ngoName = ngoName;								this.registrationNo = registrationNo;
		this.accountNo = accountNo;							this.totalMoneyCollection = totalMoneyCollection;
		this.totalBooksCollection = totalBooksCollection;	this.totalClothesCollection = totalClothesCollection;
		this.totalEdibleCollection = totalEdibleCollection;	this.totalOtherCollection = totalOtherCollection;
	}

	//Getters and Setters
	
	public String getNgoName() {
		return ngoName;
	}

	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getTotalMoneyCollection() {
		return totalMoneyCollection;
	}

	public void setTotalMoneyCollection(double totalMoneyCollection) {
		this.totalMoneyCollection = totalMoneyCollection;
	}

	public double getTotalBooksCollection() {
		return totalBooksCollection;
	}

	public void setTotalBooksCollection(double totalBooksCollection) {
		this.totalBooksCollection = totalBooksCollection;
	}

	public double getTotalClothesCollection() {
		return totalClothesCollection;
	}

	public void setTotalClothesCollection(double totalClothesCollection) {
		this.totalClothesCollection = totalClothesCollection;
	}

	public double getTotalEdibleCollection() {
		return totalEdibleCollection;
	}

	public void setTotalEdibleCollection(double totalEdibleCollection) {
		this.totalEdibleCollection = totalEdibleCollection;
	}

	public double getTotalOtherCollection() {
		return totalOtherCollection;
	}

	public void setTotalOtherCollection(double totalOtherCollection) {
		this.totalOtherCollection = totalOtherCollection;
	}

	//ToString
	
	@Override
	public String toString() {
		return "DonationBox [ngoName=" + ngoName + ", registrationNo=" + registrationNo + ", accountNo=" + accountNo
				+ ", totalMoneyCollection=" + totalMoneyCollection + ", totalBooksCollection=" + totalBooksCollection
				+ ", totalClothesCollection=" + totalClothesCollection + ", totalEdibleCollection="
				+ totalEdibleCollection + ", totalOtherCollection=" + totalOtherCollection + "]";
	}
	
}
