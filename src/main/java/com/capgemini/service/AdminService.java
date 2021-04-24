package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.*;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Employee;

public interface AdminService 
{
	public boolean addEmployee(Employee employee) throws DuplicateEmployeeException;
	public boolean modifyEmployee(Employee employee) throws NoSuchEmployeeException;
	public boolean removeEmployee(int employeeId) throws NoSuchEmployeeException;
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException;
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException;
	public List<Employee> findAllEmployee();
	public DonationDistributionStatus approveDonation(DonationDistribution distribution);
	public void addAddress(Address address);
	public boolean removeAddress(int add_Id);
}
