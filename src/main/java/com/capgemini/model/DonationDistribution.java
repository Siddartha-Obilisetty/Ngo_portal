package com.capgemini.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class DonationDistribution 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private int distributionid;
	
	private double amt_distributed;
	
	private LocalDate dod;
	
	private LocalDate app_or_rej_date;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationDistributionStatus")
	private DonationDistributionStatus status;
	

	@OneToOne
	@JoinColumn(name = "np_id")
	private NeedyPeople needyPeople;
	

	@OneToOne
	@JoinColumn(name = "item_id")
	private DonationItem donationItem;
	

	@OneToOne
	@JoinColumn(name = "empid")
	private Employee employee;
		
	public DonationDistribution() {}
	

	public DonationDistribution(int distributionid, double amt_distributed, LocalDate dod, LocalDate app_or_rej_date,
			DonationDistributionStatus status, NeedyPeople needyPeople, DonationItem donationItem, Employee employee) {
		super();
		this.distributionid = distributionid;
		this.amt_distributed = amt_distributed;
		this.dod = dod;
		this.app_or_rej_date = app_or_rej_date;
		this.status = status;
		this.needyPeople = needyPeople;
		this.donationItem = donationItem;
		this.employee = employee;
	}


	public int getDistributionid() {
		return distributionid;
	}


	public void setDistributionid(int distributionid) {
		this.distributionid = distributionid;
	}


	public double getAmt_distributed() {
		return amt_distributed;
	}


	public void setAmt_distributed(double amt_distributed) {
		this.amt_distributed = amt_distributed;
	}


	public LocalDate getDod() {
		return dod;
	}


	public void setDod(LocalDate dod) {
		this.dod = dod;
	}


	public LocalDate getApp_or_rej_date() {
		return app_or_rej_date;
	}


	public void setApp_or_rej_date(LocalDate app_or_rej_date) {
		this.app_or_rej_date = app_or_rej_date;
	}


	public DonationDistributionStatus getStatus() {
		return status;
	}
	public void setStatus(DonationDistributionStatus status) {
		this.status = status;
	}

	public NeedyPeople getNeedyPeople() {
		return needyPeople;
	}

	public void setNeedyPeople(NeedyPeople needyPeople) {
		this.needyPeople = needyPeople;
	}

	public DonationItem getDonationItem() {
		return donationItem;
	}

	public void setDonationItem(DonationItem donationItem) {
		this.donationItem = donationItem;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
