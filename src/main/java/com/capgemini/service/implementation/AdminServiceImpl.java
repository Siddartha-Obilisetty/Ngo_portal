package com.capgemini.service.implementation;
import com.capgemini.dao.AdminDao;
import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.service.AdminService;

import java.awt.Label;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	AdminDao admin;

	//not finished
			@Transactional
			@Override
			public boolean approveDonation(DonationDistribution distribution) {
				// TODO Auto-generated method stub
				return false;
			}
	
	@Transactional
	@Override
	public boolean removeAddress(int add_Id) {
	try {
		admin.deleteAddress(add_Id);
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
			e= admin.readEmployeeById(employeeId);
			if(e!=null) {
				admin.deleteEmployee(employeeId);
				this.removeAddress(e.getAddress().getAdd_Id());
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
			admin.addAddress(a.getAdd_Id(),a.getCity(),a.getState(),a.getPin(),a.getLandmark());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}	
	@Transactional
	@Override
	public boolean addEmployee(Employee e) throws DuplicateEmployeeException {
		try{
			Employee emp =null;
			emp= admin.readEmployeeById(e.getEmpid());
			if(emp==null) {
				addAddress(e.getAddress());
				int i=admin.createEmployee(e.getEmpid(),e.getEname(),e.getEmail(),e.getPhone(),e.getUsername(),e.getPassword(), e.getAddress().getAdd_Id());
				return true;
			}
			else {
				throw new DuplicateEmployeeException(e.getEmpid());
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
			e = admin.readEmployeeById(employee.getEmpid());
			if(e!=null) {
				admin.updateAddress(employee.getAddress());
				admin.updateEmployee(employee);
				return true;
			}
			else {
				throw new NoSuchEmployeeException(employee.getEmpid());
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
			e = admin.readEmployeeById(employeeId);
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
			eList = admin.readEmployeeByName(name);
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
			return admin.readAllEmployees();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	
}
