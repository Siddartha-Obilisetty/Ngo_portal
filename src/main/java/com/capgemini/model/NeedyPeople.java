package com.capgemini.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class NeedyPeople 
{
	@Id
	private int np_id;
	
	private String np_name;
	
	private String phone;
	
	private double family_income;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationType")
	private DonationType donationType;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public NeedyPeople() {}
	
	
	public NeedyPeople(int np_id, String np_name, String phone, double family_income, DonationType donationType,
			Address address) {
		super();
		this.np_id = np_id;
		this.np_name = np_name;
		this.phone = phone;
		this.family_income = family_income;
		this.donationType = donationType;
		this.address = address;
	}


	public int getNp_id() {
		return np_id;
	}


	public void setNp_id(int np_id) {
		this.np_id = np_id;
	}


	public String getNp_name() {
		return np_name;
	}


	public void setNp_name(String np_name) {
		this.np_name = np_name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public double getFamily_income() {
		return family_income;
	}


	public void setFamily_income(double family_income) {
		this.family_income = family_income;
	}

	public DonationType getDonationType() {
		return donationType;
	}


	public void setDonationType(DonationType donationType) {
		this.donationType = donationType;
	}


	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
