package com.capgemini.model;

//imports

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.Type;

//Entity class

@Entity
public class Donation 
{
	@Id
	@Column(name="donation_id")
	private int donationId;
	
	@Column(name="donation_amount")
	private double donationAmount;
	
	@Column(name="donation_date")
	private Date donationDate;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "item_id")
	private DonationItem item;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;
	
	public Donation() {}	//no parameter constructor
	
	//Parameterized constructor
	public Donation(int donationId, double donationAmount, Date donationDate, DonationItem item, Donor donor) {
		this.donationId = donationId;		this.donationAmount = donationAmount;
		this.donationDate = donationDate;	this.item = item;
		this.donor = donor;
	}

	//Getters and Setters
	
	public int getDonationId() {
		return donationId;
	}

	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}

	public double getDonationAmount() {
		return donationAmount;
	}

	public void setDonationAmount(double donationAmount) {
		this.donationAmount = donationAmount;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public DonationItem getItem() {
		return item;
	}

	public void setItem(DonationItem item) {
		this.item = item;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	//ToString
	
	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", donationAmount=" + donationAmount + ", donationDate="
				+ donationDate + ", item=" + item + ", donor=" + donor + "]";
	}

	
}
