package com.capgemini.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.AdminDao;
import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.AdminService;

@SpringBootTest
public class AdminTest 
{
	@Autowired
	AdminService adminService;
	
	@MockBean
	AdminDao adminDao;
	
	@Test
	public void addEmployeeTest() throws SQLException, DuplicateEmployeeException {
		Address a= new Address(1010,"knl","ap","501010","knl");
		Employee e = new Employee(101,"siddu","sid@g","8225","sid","sid",a);
		when(adminDao.addAddress(a)).thenReturn(1);
		when(adminDao.createEmployee(e)).thenReturn(1);
		assertEquals(true, adminService.addEmployee(e));
	}
	
	@Test
	public void addAddressTest() throws SQLException {
		Address a= new Address(1010,"knl","ap","501010","knl");
		when(adminDao.addAddress(a)).thenReturn(1);
		assertEquals(true, adminService.addAddress(a));
	}
	
	@Test
	public void removeEmployeeTest() throws SQLException, NoSuchEmployeeException {
		Address a= new Address(1010,"knl","ap","501010","knl");
		Employee e = new Employee(101,"siddu","sid@g","8225","sid","sid",a);
		when(adminDao.readEmployeeById(101)).thenReturn(e);
		when(adminDao.deleteEmployee(101)).thenReturn(1);
		when(adminDao.deleteAddress(1010)).thenReturn(1);
		assertEquals(true, adminService.removeEmployee(101));
	}
	
	@Test
	public void removeAddressTest() throws SQLException {
		when(adminDao.deleteAddress(1010)).thenReturn(1);
		assertEquals(true, adminService.removeAddress(1010));
	}
	
	@Test
	public  void modifyEmployee() throws NoSuchEmployeeException, SQLException 
	{
		Address a= new Address(1010,"knl","ap","501010","knl");
		Employee e = new Employee(101,"siddu","sid@g","8225","sid","sid",a);
		when(adminDao.readEmployeeById(101)).thenReturn(e);
		when(adminDao.updateEmployee(e)).thenReturn(1);
		when(adminDao.updateAddress(a)).thenReturn(1);
		assertEquals(true, adminService.modifyEmployee(e));
	}
	
	@Test
	public void approveDonation() {
		DonationDistribution d= new DonationDistribution();
		Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
		NeedyPeople np = new NeedyPeople(111,"sid","8888", 15000.0, "siddu", "uddis",0,ad);
		d.setDistributionId(0);
		d.setAmountDistributed(1000);
		d.setApprovalOrRejectedDate(LocalDate.now());
		d.setStatus(DonationDistributionStatus.APPROVED);
		d.setNeedyPeople(np);
		when(adminDao.getDonationDistritionByDd_id(0)).thenReturn(d);
		when(adminDao.approveDonation(d)).thenReturn(1);
		assertEquals(DonationDistributionStatus.APPROVED,adminService.approveDonation(0));	
	}
	
	@Test
	public void findAllEmployeeTest() throws SQLException { 
		Address ad1=new Address(1001,"Hyderabad","Telangana","50200","Kukatpally"); 
		Employee emp1= new Employee(101,"Madhu","madhu2705@gmail.com","098765432","madhu","madhu",ad1);
		Address ad2=new Address(1002,"Hyderabad","Telangana","50200","Kukatpally"); 
		Employee emp2= new Employee(102,"Madhu","madhu2705@gmail.com","098765432","madhu","madhu",ad2);		  
		when(adminDao.readAllEmployees()).thenReturn(Stream.of(emp1,emp2).collect(Collectors.toList()));
		assertEquals(2,adminService.findAllEmployee().size());
	}
	  
	@Test
	public void findEmployeeByIdTest() throws SQLException, NoSuchEmployeeException {
	    int id=101;
		Address ad1=new Address(1001,"Hyderabad","Telangana","50200","Kukatpally"); 
		Employee emp1= new Employee(101,"Madhu","madhu2705@gmail.com","098765432","madhu","madhu",ad1);
		when(adminDao.readEmployeeById(id)).thenReturn(emp1);
		assertEquals(emp1, adminService.findEmployeeById(id));  
	}
	  
	@Test
	public void findEmployeeByNameTest() throws SQLException, NoSuchEmployeeException {
		String name="Madhu";
		Address ad1=new Address(1001,"Hyderabad","Telangana","50200","Kukatpally"); 
		Employee emp1= new Employee(101,"Madhu","madhu2705@gmail.com","098765432","madhu","madhu",ad1);
		List<Employee> elist=Stream.of(emp1).collect(Collectors.toList());
		when(adminDao.readEmployeeByName(name)).thenReturn(elist);
		assertEquals(elist, adminService.findEmployeeByName(name));
	}


	@Test
	public void noSuchEmployeeException() throws SQLException {
		when(adminDao.readEmployeeById(101)).thenReturn(null);
		assertThrows(NoSuchEmployeeException.class, ()-> adminService.removeEmployee(101));
	}
	

	@Test
	public void duplicateEmployeeExceptionTest() throws SQLException {
		Address a= new Address(1010,"knl","ap","501010","knl");
		Employee e = new Employee(101,"siddu","sid@g","8225","sid","sid",a);
		when(adminDao.readEmployeeById(101)).thenReturn(e);
		assertThrows(DuplicateEmployeeException.class, ()->adminService.addEmployee(e));
	}
	
}
