package com.capgemini.exception;

//Exception class

public class DuplicateDonorException extends Exception
{
	private int donor_id;
	
	//constructor
	public DuplicateDonorException(int donor_id){
		this.donor_id=donor_id;
	}
	
	@Override
	public String getMessage() {
		return "The Donor with DonorId "+donor_id+" already exists!";
	}
}

