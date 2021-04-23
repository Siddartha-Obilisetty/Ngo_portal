package com.capgemini.model;

import javax.persistence.*;

@Entity
public class Address 
{
	@Id
	private int add_Id;
	
	private String city;
	
	private String state;
	
	private String pin;
	
	private String landmark;
	
	
	public Address() {}
	
	public Address(int add_Id, String city, String state, String pin, String landmark) {
		super();
		this.add_Id = add_Id;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.landmark = landmark;
	}
	
	
	public int getAdd_Id() {
		return add_Id;
	}

	public void setAdd_Id(int add_Id) {
		this.add_Id = add_Id;
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

	@Override
	public String toString() {
		return "Address [add_Id=" + add_Id + ", city=" + city + ", state=" + state + ", pin=" + pin + ", landmark="
				+ landmark + "]";
	}
	

}
