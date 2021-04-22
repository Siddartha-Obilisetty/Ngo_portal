package com.capgemini.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class DonationItem 
{
	@Id
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_desc")
	private String itemDescription;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationType")
	private DonationType donationType;
	
	public DonationItem() {}
	
	public DonationItem(int itemId, String itemDescription, DonationType donationType) {
		this.itemId = itemId;
		this.itemDescription = itemDescription;
		this.donationType = donationType;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public DonationType getDonationType() {
		return donationType;
	}
	public void setDonationType(DonationType donationType) {
		this.donationType = donationType;
	}
}
