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
import com.capgemini.model.Employee;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>
{	

	@Modifying
	@Query(value="update DonationDistribution d set d.DonationDistributionStatus=:#{#dd.status},d.app_or_rej_date=:#{#dd.app_or_rej_date} where d.distributionid=:#{#dd.distributionid}",nativeQuery = true)
	public boolean approveDonation(@Param("dd")DonationDistribution distribution);
	
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:add_Id,:city,:state,:pin,:landmark)",nativeQuery = true)
	public void addAddress(@Param("add_Id")int add_Id,@Param("city")String city,@Param("state")String state,@Param("pin")String pin,@Param("landmark")String landmark)throws SQLException;
	
	@Modifying
	@Query(value="insert into Employee (empid,ename,email,phone,username,password,add_Id) values (:empid,:ename,:email,:phone,:username,:password,:add_id)",nativeQuery = true)
	public int createEmployee(@Param("empid")int empid,@Param("ename")String ename,@Param("email")String email,@Param("phone")String phone,@Param("username")String username,@Param("password")String password,@Param("add_id")int add_id)throws SQLException;
	
	
	@Modifying
	@Query(value="update Address a set a.city=:#{#add.city},a.state=:#{#add.state},a.pin=:#{#add.pin},a.landmark=:#{#add.landmark} where a.add_Id=:#{#add.add_Id}")
	public void updateAddress(@Param("add")Address address);
	@Modifying
	@Query(value="update Employee e set e.ename=:#{#emp.ename},e.email=:#{#emp.email},e.phone=:#{#emp.phone},e.username=:#{#emp.username},e.password=:#{#emp.password} where e.empid=:#{#emp.empid}")
	public int updateEmployee(@Param("emp")Employee emp);
	
	@Modifying
	@Query(value="delete Address a where a.add_Id=:add_Id",nativeQuery = true)
	public void deleteAddress(@Param("add_Id")int add_Id)throws SQLException;
	@Modifying
	@Query(value="delete Employee e where e.empid=:empid",nativeQuery = true)
	public int deleteEmployee(@Param("empid")int employeeId)throws SQLException;
	
	@Query(value="select e from Employee e where e.empid=:employeeId")
	public Employee readEmployeeById(@Param("employeeId")int employeeId)throws SQLException;
	
	@Query(value="select e from Employee e where e.ename=?1")
	public List<Employee> readEmployeeByName(String name)throws SQLException;
	
	@Query(value="select e from Employee e")
	public List<Employee> readAllEmployees()throws SQLException;
	
	
}	
	
	/*
	@Modifying
	@Query(value="insert into Address values IN(:#{#add})",nativeQuery = true)
	@Transactional
	public void addAddress(@Param("add")Address address);
	*/
	/*
	 * 
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:#{#add.add_Id},:#{#add.city},:#{#add.state},:#{#add.pin},:#{#add.landmark})")
	@Transactional
	public void addAddress(@Param("add")Address address);
	
	@Modifying
	@Query(value="insert into Employee (empid,ename,email,phone,username,password) values (:#{#emp.empid},:#{#emp.ename},:#{#emp.email},:#{#emp.phone},:#{#emp.username},:#{#emp.password})")
	@Transactional
	public int createEmployee(@Param("emp")Employee emp);
	@Modifying
	@Query(value="insert into Employee (empid,ename,email,phone,username,password) select :empid,:ename,:email,:phone,:username,:password from Employee")
	@Transactional
	public int createEmployee(@Param("empid")int empid,@Param("ename")String ename,@Param("email")String email,@Param("phone")String phone,@Param("username")String username,@Param("password")String password);
	
	@Modifying
	@Query(value="insert into Employee (empid,ename,email,phone,username,password,add_Id) select :#{#emp.empid},:#{#emp.ename},:#{#emp.email},:#{#emp.phone},:#{#emp.username},:#{#emp.password},:#{#emp.add_Id}  from Employee")
	@Transactional
	public int createEmployee(@Param("emp") Employee emp)throws SQLException;
	#emp.empid,#emp.ename,#emp.email,#emp.phone,#emp.username,#emp.password,#emp.add_Id}
	@Modifying
	@Query(value="insert into Employee values IN(:#{#emp})",nativeQuery = true)
	@Transactional
	public int createEmployee(@Param("emp")Employee employee);
	*/

