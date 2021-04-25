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

//Service class

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	AdminDao adminDao;
	
	//Approve Donation Method
	@Transactional
	@Override
	public DonationDistributionStatus approveDonation(int np_id) {
		DonationDistribution distribution = adminDao.getDonationDistritionByNp_id(np_id);
		LocalDate d = LocalDate.now();
		System.out.println(distribution.getNeedyPeople().getNeedyPeopleId());
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
	
	@Transactional
	@Override
	public boolean removeAddress(int add_Id) {
	try {
		adminDao.deleteAddress(add_Id);
	} 
	catch (SQLException e) {
		System.out.println(e.getMessage());
		return false;
	}
	return true;
	}
	@Transactional
	@Override
	public boolean removeEmployee(int employeeId) throws NoSuchEmployeeException 
	{
		try{
			Employee e = null;
			e= adminDao.readEmployeeById(employeeId);
			if(e!=null) {
				adminDao.deleteEmployee(employeeId);
				this.removeAddress(e.getAddress().getAddressId());
				return true;
			}
			else {
				throw new NoSuchEmployeeException(employeeId);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	
	@Transactional
	public void addAddress(Address a)
	{
		//admin.addAddress(a);
		try {
			adminDao.addAddress(a);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}	
	@Transactional
	@Override
	public boolean addEmployee(Employee e) throws DuplicateEmployeeException {
		try{
			Employee emp =null;
			emp= adminDao.readEmployeeById(e.getEmployeeId());
			if(emp==null) {
				addAddress(e.getAddress());
				int i=adminDao.createEmployee(e);
				return true;
			}
			else {
				throw new DuplicateEmployeeException(e.getEmployeeId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/*
	
	@Transactional
	public boolean addEmployee(Employee employee) throws DuplicateEmployeeException {
		try{
			Employee e =null;
			e= admin.readEmployeeById(employee.getEmpid());
			if(e==null) {
				int i=admin.createEmployee(employee);
				System.out.println(i);
				return true;
			}
			else {
				throw new DuplicateEmployeeException(employee.getEmpid());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	*/
	
	@Transactional
	@Override
	public boolean modifyEmployee(Employee employee) throws NoSuchEmployeeException {
		Employee e=null;
		try{
			e = adminDao.readEmployeeById(employee.getEmployeeId());
			if(e!=null) {
				adminDao.updateAddress(employee.getAddress());
				adminDao.updateEmployee(employee);
				return true;
			}
			else {
				throw new NoSuchEmployeeException(employee.getEmployeeId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException {
		Employee e=null;
		try{
			e = adminDao.readEmployeeById(employeeId);
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

	@Transactional(readOnly = true)
	@Override
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException {
		List<Employee> eList=null;
		try{
			eList = adminDao.readEmployeeByName(name);
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
