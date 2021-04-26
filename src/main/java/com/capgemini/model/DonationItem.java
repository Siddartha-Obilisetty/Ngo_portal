package com.capgemini.model;

//imports

import javax.persistence.*;

import org.hibernate.annotations.Type;

//Entity class

@Entity
@Table(name="donation_item")
public class DonationItem 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_gen")
	@SequenceGenerator(name = "seq_gen",initialValue = 1)
	@Column(name="item_id",unique = true,updatable = false,nullable = false)
	private int itemId;
	
	@Column(name="item_description")
	private String itemDescription;
	
	//enum class
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationType")
	private DonationType type;
	
	public DonationItem() {}	//no parameter constructor

	//Parameterized constructor
	
	public DonationItem(int itemId, String itemDescription, DonationType type) {
		this.itemId = itemId;	this.itemDescription = itemDescription;
		this.type = type;
	}

	//Getters and Setters
	
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

	public DonationType getType() {
		return type;
	}

	public void setType(DonationType type) {
		this.type = type;
	}

	//ToString
	
	@Override
	public String toString() {
		return "DonationItem [itemId=" + itemId + ", itemDescription=" + itemDescription + ", type=" + type + "]";
	}
	
}
