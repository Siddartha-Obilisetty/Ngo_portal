package com.capgemini.model;

import javax.persistence.*;

@Entity
public class DonationBox 
{
	@Id
	private String ngo_name;
	
	private String reg_no;
	
	private String acc_no;
	
	private double total_collection;
	
	public DonationBox() {}

	public DonationBox(String ngo_name, String reg_no, String acc_no, double total_collection) {
		super();
		this.ngo_name = ngo_name;
		this.reg_no = reg_no;
		this.acc_no = acc_no;
		this.total_collection = total_collection;
	}

	public String getNgo_name() {
		return ngo_name;
	}

	public void setNgo_name(String ngo_name) {
		this.ngo_name = ngo_name;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public double getTotal_collection() {
		return total_collection;
	}

	public void setTotal_collection(double total_collection) {
		this.total_collection = total_collection;
	}
	
	
	
}
