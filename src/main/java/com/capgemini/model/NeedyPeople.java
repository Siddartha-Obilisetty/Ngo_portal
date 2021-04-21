package com.capgemini.model;

import javax.persistence.*;

@Entity
public class NeedyPeople 
{
	@Id
	@Column(name="np_id")
	private int needyPersonid;
	
	@Column(name="np_name")
	private String needyPersonName;
	
	@Column(name="np_phone")
	private String phone;
	
	@Column(name="np_family_income")
	private double familyIncome;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public NeedyPeople() {}
	
	public NeedyPeople(int needyPersonid, String needyPersonName, String phone, double familyIncome, Address address) {
		this.needyPersonid = needyPersonid;
		this.needyPersonName = needyPersonName;
		this.phone = phone;
		this.familyIncome = familyIncome;
		this.address = address;
	}
	public int getNeedyPersonid() {
		return needyPersonid;
	}
	public void setNeedyPersonid(int needyPersonid) {
		this.needyPersonid = needyPersonid;
	}
	public String getNeedyPersonName() {
		return needyPersonName;
	}
	public void setNeedyPersonName(String needyPersonName) {
		this.needyPersonName = needyPersonName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
