package com.capgemini.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Donation 
{
	@Id
	@Column(name="donation_id")
	private int donationId;
	
	private DonationItem item;
	
	@Column(name="donation_amount")
	private double donationAmount;
	
	@Column(name="donation_date")
	private Date donationDate;
	
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;
	
	public Donation() {}
	
	public Donation(int donationId, Donor donor, DonationItem item, double donationAmount, Date donationDate) {
		this.donationId = donationId;
		this.item = item;
		this.donationAmount = donationAmount;
		this.donationDate = donationDate;
		this.donor = donor;

	}
	public int getDonationId() {
		return donationId;
	}
	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}
	public Donor getDonor() {
		return donor;
	}
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	public DonationItem getItem() {
		return item;
	}
	public void setItem(DonationItem item) {
		this.item = item;
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
	
	
}
