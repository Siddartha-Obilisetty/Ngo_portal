package com.capgemini.model;

//imports

import javax.persistence.*;

//Entity class

@Entity
public class Address 
{	
	@Id
	@Column(name = "address_id")
	private int addressId;
	
	private String city;
	
	private String state;
	
	private String pin;
	
	private String landmark;
	
	public Address() {}		//no parameter constructor
	
	//Parameterized constructor
	public Address(int addressId, String city, String state, String pin, String landmark) {
		this.addressId = addressId;		this.city = city;
		this.state = state;				this.pin = pin;
		this.landmark = landmark;
	}


	//Getters and Setters
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	//ToString

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city + ", state=" + state + ", pin=" + pin + ", landmark="
				+ landmark + "]";
	}
	

}
