package com.capgemini.model;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Donation 
{
	@Id
	private int donation_id;
	
	private double donation_amount;
	
	private Date donation_date;
	
	@OneToOne
	@JoinColumn(name = "item_id")
	private DonationItem item;
	
	@OneToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;
	
	public Donation() {}
	
	public Donation(int donation_id, double donation_amount, Date donation_date, DonationItem item, Donor donor) {
		super();
		this.donation_id = donation_id;
		this.donation_amount = donation_amount;
		this.donation_date = donation_date;
		this.item = item;
		this.donor = donor;
	}

	public int getDonation_id() {
		return donation_id;
	}

	public void setDonation_id(int donation_id) {
		this.donation_id = donation_id;
	}

	public Date getDonation_date() {
		return donation_date;
	}

	public void setDonation_date(Date donation_date) {
		this.donation_date = donation_date;
	}
	
	
	public double getDonation_amount() {
		return donation_amount;
	}

	public void setDonatio_amount(double donation_amount) {
		this.donation_amount = donation_amount;
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
	
	
}
