package com.capgemini.exception;

//Exception class

public class NoSuchDonorException extends Exception 
{
	private int donorId=0;	
	private String donorName;
	
	//constructor with id
	public NoSuchDonorException(int donorId) {
		super();
		this.donorId = donorId;
	}

	//constructor with name
	public NoSuchDonorException(String donorName) {
		this.donorName=donorName;
	}

	@Override
	public String getMessage() {
		if(donorId!=0)
			return "The Donor with DonorId "+donorId+" doesn't exists!";
		else
			return "The Donor with DonorId "+donorName+" doesn't exists!";			
	}

}
