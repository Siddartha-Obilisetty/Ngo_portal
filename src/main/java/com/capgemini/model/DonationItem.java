package com.capgemini.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class DonationItem 
{
	@Id
	private int item_id;
	
	private String item_desc;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationType")
	private DonationType donationType;
	
	public DonationItem() {}
	
	
	public DonationItem(int item_id, String item_desc, DonationType donationType) {
		super();
		this.item_id = item_id;
		this.item_desc = item_desc;
		this.donationType = donationType;
	}

	
	public int getItem_id() {
		return item_id;
	}


	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}


	public String getItem_desc() {
		return item_desc;
	}


	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}


	public DonationType getDonationType() {
		return donationType;
	}
	public void setDonationType(DonationType donationType) {
		this.donationType = donationType;
	}
}
