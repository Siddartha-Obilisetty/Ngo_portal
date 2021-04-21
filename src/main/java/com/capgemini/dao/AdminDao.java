package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.Admin;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;

public interface AdminDao extends JpaRepository<Admin, Integer>
{
	//@Modifying
	//@Query(value="insert into employee values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
	public boolean createEmployee(Employee employee)throws SQLException;
	
	
	public Employee updateEmployee(Employee employee)throws SQLException;
	
	@Modifying
	@Query(value="delete from employee wher emp_id=?1",nativeQuery = true)
	public boolean deleteEmployee(int employeeId)throws SQLException;
	
	@Query(value="select e from Employee e where emp_id=?1")
	public Employee readEmployeeById(int employeeId)throws SQLException;
	
	@Query(value="select e from Employee e where emp_name=?1")
	public List<Employee> readEmployeeByName(String name)throws SQLException;
	
	@Query(value="select e from Employee e")
	public List<Employee> readAllEmployees()throws SQLException;
	
	public boolean approveDonation(DonationDistribution distribution);

}
