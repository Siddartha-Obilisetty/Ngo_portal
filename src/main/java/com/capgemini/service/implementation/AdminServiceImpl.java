package com.capgemini.service.implementation;

//imports

import com.capgemini.dao.AdminDao;
import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Donor;
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
				if(employee.getEmployeeName()==null) {
					employee.setEmployeeName(e.getEmployeeName());
				}
				if(employee.getUsername()==null) {
					employee.setUsername(e.getUsername());
				}
				if(employee.getPassword()==null) {
					employee.setPassword(e.getPassword());
				}
				if(employee.getEmail()==null) {
					employee.setEmail(e.getEmail());
				}
				if(employee.getPhone()==null) {
					employee.setPhone(e.getPhone());
				}
				if(employee.getAddress().getAddressId()==0) {
					employee.getAddress().setAddressId(e.getAddress().getAddressId());;
				}
				if(employee.getAddress().getCity()==null) {
					employee.getAddress().setCity(e.getAddress().getCity());;
				}
				if(employee.getAddress().getState()==null) {
					employee.getAddress().setState(e.getAddress().getState());;
				}
				if(employee.getAddress().getPin()==null) {
					employee.getAddress().setPin(e.getAddress().getPin());;
				}
				if(employee.getAddress().getLandmark()==null) {
					employee.getAddress().setLandmark(e.getAddress().getLandmark());;
				}
				int i=0;
				int c=0;
				if(employee.getAddress().getAddressId()!=e.getAddress().getAddressId()) {
					i=adminDao.addAddress(employee.getAddress());
					c=1;
				}
				else {
					i=adminDao.updateAddress(employee.getAddress());
				}
				if(i!=0)
					i=adminDao.updateEmployee(employee);
				if(i!=0)
					if(c==1)
						adminDao.deleteAddress(e.getAddress().getAddressId());
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
	public DonationDistributionStatus approveDonation(int dd_id) {
		DonationDistribution distribution = adminDao.getDonationDistritionByDd_id(dd_id);
		System.out.println(distribution);
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
	
	@Transactional(readOnly = true)
	@Override
	public List<DonationDistribution> findAllDonationDistribution() {
		try{
			return adminDao.getAllDonationDistrition();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
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

	@Transactional(readOnly = true)
	@Override
	public List<Donor> findAllDonor() {
		try{
			return adminDao.readAllDonors();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Donor findDonorById(int donorid) throws NoSuchDonorException {
		try{
			Donor d = adminDao.readDonorById(donorid);
			if(d!=null) {
				return d;
			}
			else {
				throw new NoSuchDonorException(donorid);
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	
}
