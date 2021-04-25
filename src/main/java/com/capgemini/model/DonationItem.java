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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "item_generator",sequenceName = "item_seq",allocationSize = 50)
	@Column(name="item_id")
	private Long itemId;
	
	@Column(name="item_description")
	private String itemDescription;
	
	//enum class
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationType")
	private DonationType type;
	
	public DonationItem() {}	//no parameter constructor

	//Parameterized constructor
	
	public DonationItem(Long itemId, String itemDescription, DonationType type) {
		this.itemId = itemId;	this.itemDescription = itemDescription;
		this.type = type;
	}

	//Getters and Setters
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
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
