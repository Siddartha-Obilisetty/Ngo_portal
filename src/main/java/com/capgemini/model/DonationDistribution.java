package com.capgemini.model;

//imports

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.Type;

//Entity Class

@Entity
@Table(name="donation_distribution")
public class DonationDistribution 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="distributionId_generator",sequenceName = "distribution_seq",allocationSize = 50)
	@Column(name = "distribution_id")
	private Long distributionId;
	
	@Column(name="amount_distributed")
	private double amountDistributed;
	
	@Column(name="date_of_distribution")
	private LocalDate dateOfDistribution;
	
	@Column(name="approval_or_rejected_date")
	private LocalDate approvalOrRejectedDate;
	
	//enum class
	@Enumerated(EnumType.STRING)
	@Type(type = "com.capgemini.model.DonationDistributionStatus")
	private DonationDistributionStatus status;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "needy_people_id")
	private NeedyPeople needyPeople;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "item_id")
	private DonationItem donationItem;
	
	//OneToOne Unidirectional Mapping
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
		
	public DonationDistribution() {}	//no parameter constructor

	//Parameterized constructor
	
	public DonationDistribution(Long distributionId, double amountDistributed, LocalDate dateOfDistribution,
			LocalDate approvalOrRejectedDate, DonationDistributionStatus status, NeedyPeople needyPeople,
			DonationItem donationItem, Employee employee) {
		this.distributionId = distributionId;			this.amountDistributed = amountDistributed;
		this.dateOfDistribution = dateOfDistribution;	this.approvalOrRejectedDate = approvalOrRejectedDate;
		this.status = status;							this.needyPeople = needyPeople;
		this.donationItem = donationItem;				this.employee = employee;
	}

	
	//Getters and Setters
	
	public Long getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(Long distributionId) {
		this.distributionId = distributionId;
	}

	public double getAmountDistributed() {
		return amountDistributed;
	}

	public void setAmountDistributed(double amountDistributed) {
		this.amountDistributed = amountDistributed;
	}

	public LocalDate getDateOfDistribution() {
		return dateOfDistribution;
	}

	public void setDateOfDistribution(LocalDate dateOfDistribution) {
		this.dateOfDistribution = dateOfDistribution;
	}

	public LocalDate getApprovalOrRejectedDate() {
		return approvalOrRejectedDate;
	}

	public void setApprovalOrRejectedDate(LocalDate approvalOrRejectedDate) {
		this.approvalOrRejectedDate = approvalOrRejectedDate;
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

	//ToString
	
	@Override
	public String toString() {
		return "DonationDistribution [distributionId=" + distributionId + ", amountDistributed=" + amountDistributed
				+ ", dateOfDistribution=" + dateOfDistribution + ", approvalOrRejectedDate=" + approvalOrRejectedDate
				+ ", status=" + status + ", needyPeople=" + needyPeople + ", donationItem=" + donationItem
				+ ", employee=" + employee + "]";
	}
	
}
