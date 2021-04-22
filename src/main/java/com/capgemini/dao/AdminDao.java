package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Admin;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;

public interface AdminDao extends JpaRepository<Admin, Integer>
{
	@Modifying
	@Query(value="insert into employee values(:#{#emp})",nativeQuery = true)
	public boolean createEmployee(@Param("emp") Employee employee)throws SQLException;
	
	@Modifying
	@Query(value="update employee set emp_name=:#{#emp.empployeeName},emp_email=:#{#emp.email},emp_phone=:#{#emp.phone},emp_username=:#{#emp.username},emp_password=:#{#emp.password} where emp_id=:#{#emp.empployeeId}",nativeQuery = true)
	public Employee updateEmployee(@Param("emp")Employee emp);
	
	@Modifying
	@Query(value="delete from employee wher emp_id=:empid",nativeQuery = true)
	public boolean deleteEmployee(@Param("empid")int employeeId)throws SQLException;
	
	@Query(value="select * from employee where emp_id=?1",nativeQuery = true)
	public Employee readEmployeeById(int employeeId)throws SQLException;
	
	@Query(value="select e from Employee e where emp_name=?1")
	public List<Employee> readEmployeeByName(String name)throws SQLException;
	
	@Query(value="select e from Employee e")
	public List<Employee> readAllEmployees()throws SQLException;
	
	//public boolean approveDonation(DonationDistribution distribution);

}
