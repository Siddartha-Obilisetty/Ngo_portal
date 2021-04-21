package com.capgemini.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class DonationDistribution 
{
	@Id
	private int distributionid;
	
	@Column(name="amt_distributed")
	private double amountDistributed;
	
	@Column(name="dod")
	private Date dateOfDistribution;
	
	@Column(name="app_or_rej_date")
	private Date approvalOrRejectedDate;
	
	@Column(name="status")
	private DonationDistributionStatus status;
	
	@Column(name="needy_people_name")
	private NeedyPeople needyPeople;
	
	@Column(name="donation_item")
	private DonationItem donationItem;
	
	@Column(name="Employee_assg")
	private Employee employee;
		
	public DonationDistribution() {}
	
	public DonationDistribution(int distributionid, double amountDistributed, Date dateOfDistribution,
			Date approvalOrRejectedDate, DonationDistributionStatus status) {
		this.distributionid = distributionid;
		this.amountDistributed = amountDistributed;
		this.dateOfDistribution = dateOfDistribution;
		this.approvalOrRejectedDate = approvalOrRejectedDate;
		this.status = status;
	}
	public int getDistributionid() {
		return distributionid;
	}
	public void setDistributionid(int distributionid) {
		this.distributionid = distributionid;
	}
	public double getAmountDistributed() {
		return amountDistributed;
	}
	public void setAmountDistributed(double amountDistributed) {
		this.amountDistributed = amountDistributed;
	}
	public Date getDateOfDistribution() {
		return dateOfDistribution;
	}
	public void setDateOfDistribution(Date dateOfDistribution) {
		this.dateOfDistribution = dateOfDistribution;
	}
	public Date getApprovalOrRejectedDate() {
		return approvalOrRejectedDate;
	}
	public void setApprovalOrRejectedDate(Date approvalOrRejectedDate) {
		this.approvalOrRejectedDate = approvalOrRejectedDate;
	}
	public DonationDistributionStatus getStatus() {
		return status;
	}
	public void setStatus(DonationDistributionStatus status) {
		this.status = status;
	}
	
}
