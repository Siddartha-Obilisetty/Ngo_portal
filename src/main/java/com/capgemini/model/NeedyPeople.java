package com.capgemini.model;

//imports

import javax.persistence.*;

import org.hibernate.annotations.Type;

//Entity class

@Entity
@Table(name="needy_people")
public class NeedyPeople 
{
	@Id
	@Column(name="needy_people_id")
	private int needyPeopleId;
	
	@Column(name="needy_people_name")
	private String needyPeopleName;
	
	private String phone;
	
	@Column(name="family_income")
	private double familyIncome;
	
	private String username;
	
	private String password;
	
	private int request;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public NeedyPeople() {}		//no parameter constructor

	//Parameterized constructor
	public NeedyPeople(int needyPeopleId, String needyPeopleName, String phone, double familyIncome, String username,
			String password, int request, Address address) {
		this.needyPeopleId = needyPeopleId;		this.needyPeopleName = needyPeopleName;
		this.phone = phone;						this.familyIncome = familyIncome;
		this.username = username;				this.password = password;
		this.request = request;					this.address = address;
	}

	//Getters and Setters
	
	public int getNeedyPeopleId() {
		return needyPeopleId;
	}

	public void setNeedyPeopleId(int needyPeopleId) {
		this.needyPeopleId = needyPeopleId;
	}

	public String getNeedyPeopleName() {
		return needyPeopleName;
	}

	public void setNeedyPeopleName(String needyPeopleName) {
		this.needyPeopleName = needyPeopleName;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRequest() {
		return request;
	}

	public void setRequest(int request) {
		this.request = request;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	//ToString
	
	@Override
	public String toString() {
		return "NeedyPeople [needyPeopleId=" + needyPeopleId + ", needyPeopleName=" + needyPeopleName + ", phone="
				+ phone + ", familyIncome=" + familyIncome + ", username=" + username + ", password=" + password
				+ ", request=" + request + ", address=" + address + "]";
	}
	
	
	
	


}
