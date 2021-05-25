package com.capgemini.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Donor;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;

@SpringBootTest
class EmployeeTest
{
	@Autowired
	EmployeeService employeeService;
	
	@MockBean
	EmployeeDao employeeDao;
	

	@Test
	public void loginTest() throws NoSuchEmployeeException, WrongCredentialsException
	{
	    
		Address a= new Address(1010,"knl","ap","501010","knl");
		Employee e = new Employee(101,"siddu","sid@g","8225","sid","sid",a);
        Optional<Employee> oe = Optional.of(e);
	    String username="sid";
		String password="sid";
		when(employeeDao.findByUsername(username)).thenReturn(oe);
		when(employeeDao.login(username)).thenReturn(password);
		assertEquals(true,employeeService.login(username, password));
	}

	
	@Test
	public void addNeedyPersonTest() throws SQLException, DuplicateNeedyPeopleException {
		Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
		NeedyPeople np = new NeedyPeople(111,"sid","8888", 20000.0, "siddu", "uddis",0,ad);
		when(employeeDao.addAddress(ad)).thenReturn(1);
		when(employeeDao.createNeedyPerson(np)).thenReturn(1);
		assertEquals(true, employeeService.addNeedyPerson(np));
	}
	
	@Test
	public void addAddressTest() throws SQLException {
		Address a= new Address(1010,"knl","ap","501010","knl");
		when(employeeDao.addAddress(a)).thenReturn(1);
		assertEquals(true, employeeService.addAddress(a));
	}
	
	@Test
	public void removeNeedyPeopleTest() throws SQLException, NoSuchNeedyPeopleException {
		Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
		Optional<NeedyPeople> np = Optional.of(new NeedyPeople(111,"sid","8888", 20000.0, "siddu", "uddis",0,ad));
		when(employeeDao.readNeedyPeopleById(111)).thenReturn(np);
		when(employeeDao.deleteAddress(1011)).thenReturn(1);
		when(employeeDao.deleteNeedyPerson(111)).thenReturn(1);
		assertEquals(true, employeeService.removeNeedyPerson(111));
	}
	
	@Test
	public void removeAddressTest() throws SQLException {
		when(employeeDao.deleteAddress(1010)).thenReturn(1);
		assertEquals(true, employeeService.removeAddress(1010));
	}
	
	
	//helpneedyperson
//	@Test
//	public void helpNeedyPerson() 
//	{
//		int dd_id=0;
//		
//		DonationDistribution d= new DonationDistribution();
//		d.setDistributionId(0);
//		d.setAmountDistributed(1000);
//		d.setStatus(DonationDistributionStatus.APPROVED);
//		d.setDateOfDistribution(LocalDate.now());
//		when(employeeDao.getDonationDistritionByNp_id(dd_id)).thenReturn((List<DonationDistribution>) d);
//		when(employeeDao.helpNeedyPerson(d)).thenReturn(1);
//		when(employeeDao.deductAmountAfterApproval(d.getAmountDistributed())).thenReturn(1);
//		assertEquals("Approved and Amount Deducted", employeeService.helpNeedyPerson(dd_id, dd_id));
//	}
	
	
	
	@Test
	public void findAllNeedyPeopleTest() throws SQLException { 
		Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
		NeedyPeople np = new NeedyPeople(111,"sid","8888", 20000.0, "siddu", "uddis",0,ad);
		Address ad2=new Address(1002,"Hyderabad","Telangana","50200","Kukatpally"); 
		NeedyPeople np2 = new NeedyPeople(112,"siddu","238888", 10000.0, "sid", "dis",0,ad2);
		when(employeeDao.readAllNeedyPeople()).thenReturn(Stream.of(np,np2).collect(Collectors.toList()));
		assertEquals(2,employeeService.findAllNeedyPeople().size());
	}
	  
	@Test
	public void findNeedyPeopleByIdTest() throws SQLException, NoSuchNeedyPeopleException {
	    int id=111;
	    Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
		Optional<NeedyPeople> np = Optional.of(new NeedyPeople(111,"sid","8888", 20000.0, "siddu", "uddis",0,ad));
		when(employeeDao.readNeedyPeopleById(id)).thenReturn(np);
		assertEquals(np.get(), employeeService.findNeedyPeopleById(id));  
	}
	  
	@Test
	public void findNeedyPeopleByNameTest() throws SQLException, NoSuchNeedyPeopleException {
		String name="siddu";
	    Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
		NeedyPeople np = new NeedyPeople(111,"siddu","8888", 20000.0, "siddu", "uddis",0,ad);
		Address ad2=new Address(1002,"Hyderabad","Telangana","50200","Kukatpally"); 
		NeedyPeople np2 = new NeedyPeople(112,"siddu","238888", 10000.0, "sid", "dis",0,ad2);
		List<NeedyPeople> elist=Stream.of(np,np2).collect(Collectors.toList());
		when(employeeDao.readNeedyPeopleByName(name)).thenReturn(elist);
		assertEquals(elist, employeeService.findNeedyPeopleByName(name));
	}

	@Test
	public void noSuchEmployeeException() throws SQLException {
		when(employeeDao.findByUsername("siddu")).thenReturn(Optional.ofNullable(null));
		assertThrows(NoSuchEmployeeException.class, ()-> employeeService.login("siddu", "uddis"));
	}
	
	@Test
	public void noSuchNeedyPeopleException() throws SQLException {
		when(employeeDao.readNeedyPeopleById(101)).thenReturn(Optional.ofNullable(null));
		assertThrows(NoSuchNeedyPeopleException.class, ()-> employeeService.removeNeedyPerson(101));
	}
	

	@Test
	public void duplicateNeedyPeopleExceptionTest() throws SQLException {
		Address ad2=new Address(1002,"Hyderabad","Telangana","50200","Kukatpally"); 
		NeedyPeople np2 = new NeedyPeople(101,"siddu","238888", 10000.0, "sid", "dis",0,ad2);
		Optional<NeedyPeople> onp=Optional.of(np2);
		when(employeeDao.readNeedyPeopleById(101)).thenReturn(onp);
		assertThrows(DuplicateNeedyPeopleException.class, ()->employeeService.addNeedyPerson(np2));
	}
	
	@Test
	public void wrongCredentialsExceptionTest() throws SQLException
	{
		
		Address a= new Address(1010,"knl","ap","501010","knl");
		Employee e = new Employee(101,"siddu","sid@g","8225","sid","sid",a);
        Optional<Employee> oe = Optional.of(e);
	    String username="sid";
		when(employeeDao.findByUsername(username)).thenReturn(oe);
        when(employeeDao.login("varu")).thenReturn("varu");
		assertThrows(WrongCredentialsException.class, ()->employeeService.login(username, "varun"));
	}
	
}
