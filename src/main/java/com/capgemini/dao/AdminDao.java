package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Address;
import com.capgemini.model.Admin;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Donor;
import com.capgemini.model.Employee;

//Repository class

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>
{	
	//creating employee
	@Modifying
	@Query(value="insert into Employee (employee_id,employee_name,email,phone,username,password,address_id)"+
			"values (:#{#emp.getEmployeeId()},:#{#emp.getEmployeeName()},:#{#emp.getEmail()},"+
			":#{#emp.getPhone()},:#{#emp.getUsername()},:#{#emp.getPassword()},:#{#emp.getAddress().getAddressId()}"+
			")",nativeQuery = true)
	public int createEmployee(@Param("emp")Employee employee)throws SQLException;
	
	//modifying employee
	@Modifying
	@Query(value="update Employee e set e.employee_name=:#{#emp.getEmployeeName()},e.email=:#{#emp.getEmail()},"
			+"e.phone=:#{#emp.getPhone()},e.username=:#{#emp.getUsername()},e.password=:#{#emp.getPassword()},"
			+"e.address_id=:#{#emp.getAddress().getAddressId()} where e.employee_id=:#{#emp.getEmployeeId()}"
			,nativeQuery = true)
	public int updateEmployee(@Param("emp")Employee emp);
	
	//deleting employee
	@Modifying
	@Query(value="delete Employee e where e.employeeId=:empid")
	public int deleteEmployee(@Param("empid")int employeeId)throws SQLException;
	
	//adding address
	@Modifying
	@Query(value="insert into Address (address_id,city,state,pin,landmark) values(:#{#add.getAddressId()},"+
			":#{#add.getCity()},:#{#add.getState()},:#{#add.getPin()},:#{#add.getLandmark()})",nativeQuery = true)
	public int addAddress(@Param("add")Address address)throws SQLException;
	
	//modifying address
	@Modifying
	@Query(value="update Address a set a.city=:#{#add.getCity()},a.state=:#{#add.getState()},a.pin=:#{#add.getPin()},"+
			"a.landmark=:#{#add.getLandmark()} where a.address_Id=:#{#add.getAddressId()}",nativeQuery = true)
	public int updateAddress(@Param("add")Address address);
	
	//deleting address
	@Modifying
	@Query(value="delete Address a where a.addressId=:add_id")
	public int deleteAddress(@Param("add_id")int add_id)throws SQLException;
	
	//modifying DonationDistribution
	@Modifying
	@Query(value="update Donation_Distribution set status=:#{#dd.getStatus()},"+
			"approval_Or_Rejected_Date=:#{#dd.getApprovalOrRejectedDate()} where distribution_Id="+
			":#{#dd.getDistributionId()}",nativeQuery = true)
	public int approveDonation(@Param("dd")DonationDistribution distribution);
	
	//extracting DonationDistrition data using Needy person id
	@Query(value="select d from DonationDistribution d where d.distributionId=:dd_id")
	public DonationDistribution getDonationDistritionByDd_id(@Param("dd_id")int dd_id);
	
	@Query(value="select d from DonationDistribution d")
	public List<DonationDistribution> getAllDonationDistrition()throws SQLException;
	
	//extracting Employee data using employee id
	@Query(value="select e from Employee e where e.employeeId=?1")
	public Employee readEmployeeById(int employeeId)throws SQLException;
	
	//extracting Employee data using employee name
	@Query(value="select e from Employee e where e.employeeName=?1")
	public List<Employee> readEmployeeByName(String name)throws SQLException;
	
	//extracting Employee data
	@Query(value="select e from Employee e")
	public List<Employee> readAllEmployees()throws SQLException;

	@Query(value="select d from Donor d")
	public List<Donor> readAllDonors()throws SQLException;

	@Query(value="select d from Donor d where d.donorId=?1")
	public Donor readDonorById(int donorid)throws SQLException;
		
}