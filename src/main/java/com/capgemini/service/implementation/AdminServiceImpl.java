package com.capgemini.service.implementation;

//imports

import com.capgemini.dao.AdminDao;
import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Employee;
import com.capgemini.service.AdminService;

import java.awt.Label;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Service Implementation class

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	AdminDao adminDao;
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	//creating employee
	@Transactional
	@Override
	public boolean addEmployee(Employee e) throws DuplicateEmployeeException {
		try{
			Employee emp =	adminDao.readEmployeeById(e.getEmployeeId());
			if(emp==null) {
				addAddress(e.getAddress());
				int i=adminDao.createEmployee(e);
				if(i!=0)
					return true;
			}
			else {
				throw new DuplicateEmployeeException(e.getEmployeeId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
	//adding address
	@Transactional
	public boolean addAddress(Address a)
	{
		try {
			int i=adminDao.addAddress(a);
			if(i!=0)
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}	
	
	//removing employee
	@Transactional
	@Override
	public boolean removeEmployee(int employeeId) throws NoSuchEmployeeException 
	{
		try{
			Employee e = adminDao.readEmployeeById(employeeId);
			if(e!=null) {
				int i=adminDao.deleteEmployee(employeeId);
				if(i!=0)
					if(removeAddress(e.getAddress().getAddressId()))
						return true;
			}
			else {
				throw new NoSuchEmployeeException(employeeId);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	@Transactional
	@Override
	public boolean removeAddress(int add_Id)
	{
		try {
			int i=adminDao.deleteAddress(add_Id);
			if(i!=0)
				return true;
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//updating employee
	@Transactional
	@Override
	public boolean modifyEmployee(Employee employee) throws NoSuchEmployeeException {
		try{
			Employee e = adminDao.readEmployeeById(employee.getEmployeeId());
			if(e!=null) {
				int i=adminDao.updateAddress(employee.getAddress());
				if(i!=0)
					i=adminDao.updateEmployee(employee);
				if(i!=0)
					return true;
			}
			else {
				throw new NoSuchEmployeeException(employee.getEmployeeId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	
	//Approve Donation Method
	@Transactional
	@Override
	public DonationDistributionStatus approveDonation(int np_id) {
		DonationDistribution distribution = adminDao.getDonationDistritionByNp_id(np_id);
		LocalDate d = LocalDate.now();
		if(distribution.getNeedyPeople().getFamilyIncome()<=200000.0) {
			distribution.setStatus(DonationDistributionStatus.APPROVED);
			distribution.setApprovalOrRejectedDate(d);
			adminDao.approveDonation(distribution);
			return DonationDistributionStatus.APPROVED;
		}
		else {
			distribution.setStatus(DonationDistributionStatus.REJECTED);
			distribution.setApprovalOrRejectedDate(d);
			adminDao.approveDonation(distribution);
			return DonationDistributionStatus.REJECTED;
		}
	}
	
	//Reading employee data by id
	@Transactional(readOnly = true)
	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException 
	{
		try{
			Employee e = adminDao.readEmployeeById(employeeId);
			if(e!=null) {
				return e;
			}
			else {
				throw new NoSuchEmployeeException(employeeId);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	//Reading employee data by name
	@Transactional(readOnly = true)
	@Override
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException
	{
		try{
			List<Employee> eList = adminDao.readEmployeeByName(name);
			if(eList.size()!=0) {
				return eList;
			}
			else {
				throw new NoSuchEmployeeException(name);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	//Reading all employee data
	@Transactional(readOnly = true)
	@Override
	public List<Employee> findAllEmployee() {
		try{
			return adminDao.readAllEmployees();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	
}
