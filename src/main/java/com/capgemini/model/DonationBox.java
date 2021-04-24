package com.capgemini.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class DonationBox 
{
	@Id
	private String ngo_name="Sprint NGO";
	
	private String reg_no="1234";
	
	private String acc_no="xxxxxxxxxxxx2021";
	
	private double total_money_collection=10000.0;
	
	private double total_books_collection=0L;
	
	private double total_clothes_collection=0L;
	
	private double total_edible_collection=0L;
	
	private double total_other_collection=0L;
	
	
	public DonationBox() {}

	
	public DonationBox(String ngo_name, String reg_no, String acc_no, double total_money_collection,
			double total_books_collection, double total_clothes_collection, double total_edible_collection,
			double total_other_collection) {
		this.ngo_name = ngo_name;
		this.reg_no = reg_no;
		this.acc_no = acc_no;
		this.total_money_collection = total_money_collection;
		this.total_books_collection = total_books_collection;
		this.total_clothes_collection = total_clothes_collection;
		this.total_edible_collection = total_edible_collection;
		this.total_other_collection = total_other_collection;
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

	public double getTotal_money_collection() {
		return total_money_collection;
	}

	public void setTotal_collection(double total_money_collection) {
		this.total_money_collection = total_money_collection;
	}


	public double getTotal_books_collection() {
		return total_books_collection;
	}


	public void setTotal_books_collection(double total_books_collection) {
		this.total_books_collection = total_books_collection;
	}


	public double getTotal_clothes_collection() {
		return total_clothes_collection;
	}


	public void setTotal_clothes_collection(double total_clothes_collection) {
		this.total_clothes_collection = total_clothes_collection;
	}


	public double getTotal_edible_collection() {
		return total_edible_collection;
	}


	public void setTotal_edible_collection(double total_edible_collection) {
		this.total_edible_collection = total_edible_collection;
	}


	public double getTotal_other_collection() {
		return total_other_collection;
	}


	public void setTotal_other_collection(double total_other_collection) {
		this.total_other_collection = total_other_collection;
	}
	
	
	
}
