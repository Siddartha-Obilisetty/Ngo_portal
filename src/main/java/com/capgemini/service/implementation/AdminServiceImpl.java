package com.capgemini.service.implementation;
import com.capgemini.dao.AdminDao;
import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.service.AdminService;

import java.sql.SQLException;
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
	public boolean addEmployee(Employee employee) throws DuplicateEmployeeException {
		try{
			Employee e = admin.readEmployeeById(employee.getEmployeeId());
			if(e!=null) {
				return admin.createEmployee(employee);
			}
			else {
				throw new DuplicateEmployeeException(employee.getEmployeeId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Transactional
	@Override
	public Employee modifyEmployee(Employee employee) throws NoSuchEmployeeException {
		Employee e=null;
		try{
			e = admin.readEmployeeById(employee.getEmployeeId());
			if(e!=null) {
				return admin.updateEmployee(employee);
			}
			else {
				throw new NoSuchEmployeeException(employee.getEmployeeId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Transactional
	@Override
	public boolean removeEmployee(int employeeId) throws NoSuchEmployeeException {
		try{
			Employee e = admin.readEmployeeById(employeeId);
			if(e!=null) {
				return admin.deleteEmployee(employeeId);
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

	@Transactional(readOnly = true)
	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException {
		Employee e=null;
		try{
			e = admin.readEmployeeById(employeeId);
			if(e!=null) {
				e = admin.readEmployeeById(employeeId);
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
			if(eList!=null) {
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
		List<Employee> eList=null;
		try{
			eList = admin.readAllEmployees();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return eList;
	}

}
