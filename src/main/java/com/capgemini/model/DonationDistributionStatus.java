package com.capgemini.model;

public enum DonationDistributionStatus {
	PENDING(0), APPROVED(1), REJECTED(2);
	private final int status;
	DonationDistributionStatus(int status){
		this.status=status;
	}
	public int getStatus() {
		return status;
	}
	
}
