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
	
	@Column(name="total_books_collection")
	private double totalBooksCollection;
	
	@Column(name="total_clothes_collection")
	private double totalClothesCollection;
	
	@Column(name="total_food_collection")
	private double totalFoodCollection;
	
	@Column(name="total_extras_collection")
	private double totalExtrasCollection;
	
	public DonationBox() {}
	
	public DonationBox(String ngoName, String registrationNumber, String accountNumber, double totalCollection,
			double totalBooksCollection, double totalClothesCollection, double totalFoodCollection,
			double totalExtrasCollection) {
		this.ngoName = ngoName;
		this.registrationNumber = registrationNumber;
		this.accountNumber = accountNumber;
		this.totalCollection = totalCollection;
		this.totalBooksCollection = totalBooksCollection;
		this.totalClothesCollection = totalClothesCollection;
		this.totalFoodCollection = totalFoodCollection;
		this.totalExtrasCollection = totalExtrasCollection;
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

	public double getTotalFoodCollection() {
		return totalFoodCollection;
	}

	public void setTotalFoodCollection(double totalFoodCollection) {
		this.totalFoodCollection = totalFoodCollection;
	}

	public double getTotalExtrasCollection() {
		return totalExtrasCollection;
	}

	public void setTotalExtrasCollection(double totalExtrasCollection) {
		this.totalExtrasCollection = totalExtrasCollection;
	}
	
	
}
