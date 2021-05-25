package com.capgemini.model;

//Enum Class

public enum DonationDistributionStatus {
	//enum values
	PENDING(0),
	APPROVED(1),
	REJECTED(2);
	
	private final int status;
	
	//Parameterized constructor
	
	DonationDistributionStatus(int status){
		this.status=status;
	}
	
	//Getter
	public int getStatus() {
		return status;
	}
	
}
