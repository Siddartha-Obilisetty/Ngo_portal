package com.capgemini.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

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
	
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationDistributionStatus")
	private DonationDistributionStatus status;
	
	@OneToOne
	@JoinColumn(name="item_id")
	private DonationItem donationItem;
	
	@OneToOne
	@JoinColumn(name = "np_id")
	private NeedyPeople needyPeople;

	@OneToOne
	@JoinColumn(name="emp_id")
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

	public DonationItem getDonationItem() {
		return donationItem;
	}

	public void setDonationItem(DonationItem donationItem) {
		this.donationItem = donationItem;
	}

	public NeedyPeople getNeedyPeople() {
		return needyPeople;
	}

	public void setNeedyPeople(NeedyPeople needyPeople) {
		this.needyPeople = needyPeople;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
