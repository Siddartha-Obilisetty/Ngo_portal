package com.capgemini.donor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.DonorDao;
import com.capgemini.exception.DuplicateDonorException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.DonationItem;
import com.capgemini.model.DonationType;
import com.capgemini.model.Donor;
import com.capgemini.service.DonorService;

@SpringBootTest
public class DonorTest 
{

	
	@Autowired
	DonorService donorService;
	
	@MockBean
	DonorDao donorDao;
	
	DonorService donorService1=mock(DonorService.class);
	
	@Test
	public void registerDonorTest() throws DuplicateDonorException, SQLException
	{
		Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
        Donor d = new Donor(125,"Akhil","akhi31@gmail.com","1122334455","akhil","akhil", ad);
        when(donorDao.addAddress(ad)).thenReturn(1);
        when(donorDao.createDonor(d)).thenReturn(1);
        assertEquals(true,donorService.registerDonor(d));
    }
	

	@Test
	public void addAddressTest() throws SQLException {
        Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
        when(donorDao.addAddress(ad)).thenReturn(1);
        assertEquals(true,donorService.addAddress(ad));

	}
	
	@Test
	public void loginTest() throws WrongCredentialsException, NoSuchDonorException
	{
	    
		Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
        Optional<Donor> d = Optional.of(new Donor(125,"Akhil","akhi31@gmail.com","1122334455","sahithi","sahithi", ad));
	    String username="sahithi";
		String password="sahiti";
		when(donorDao.findByUsername(username)).thenReturn(d);
		when(donorDao.login(username)).thenReturn(password);
		assertEquals(true,donorService.login(username, password));
	}
	
	
	@Test
	public void forgotPasswordTest() throws WrongCredentialsException
	{
		Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
		 Optional<Donor> d = Optional.of(new Donor(125,"Akhil","akhi31@gmail.com","1122334455","sahithi","sahithi", ad));
		 String username="sahithi";
		 when(donorDao.findByUsername(username)).thenReturn(d);
		 assertEquals("Your password has been sent to akhi31@gmail.com",donorService.forgotPassword(username));
    }
	
	
	@Test
	public void resetPasswordTest() throws WrongCredentialsException
	{
		String username="sahithi";
		String oldPassword="sahithi";
		String newPassword="sahith";
		Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
		 Optional<Donor> d = Optional.of(new Donor(125,"Akhil","akhi31@gmail.com","1122334455","sahithi","sahithi", ad));
		 when(donorDao.findByUsername(username)).thenReturn(d);
		 when(donorDao.resetPassword(username,newPassword)).thenReturn(1);
		 assertEquals("Your password has been Changed",donorService.resetPassword(username,oldPassword, newPassword));
    }
	
	
	@Test
	public void donateToNGOTest()
	{
		Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
		Donor d = new Donor(125,"Akhil","akhi31@gmail.com","1122334455","sahithi","sahithi", ad);
		DonationItem di=new  DonationItem(1,"money",DonationType.MONEY);
		Donation dt=new Donation(101,1000.0,LocalDate.now(),di,d);
		when(donorDao.donateToNGO(dt)).thenReturn(1);
		assertEquals(true,donorService.donateToNGO(dt));
	}
	
	@Test
	public void sendThankyouMailToDonatorTest() {
		String email="siddu@gmail.com";
		donorService1.sendThankyouMailToDonator(email);
		verify(donorService1,times(1)).sendThankyouMailToDonator(email);
	}
	
	@Test
	public void emailPasswordToDonor() {
		String email="sid@gmail.com";
		String password="sid";
		donorService1.emailPasswordToDonor(email, password);
		verify(donorService1,times(1)).emailPasswordToDonor(email, password);
	}

	@Test
	public void NoSuchDonorExceptionTest()throws SQLException
	{
		 Optional<Donor> dr =Optional.ofNullable(null);
		 String username="var";
		 String password = "varu";
		 when(donorDao.findByUsername(username)).thenReturn(dr);
		 assertThrows(NoSuchDonorException.class, ()->donorService.login(username,password));
	}


	@Test
    public void duplicateDonorExceptionTest() throws SQLException
	{
        Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
        Donor d = new Donor(101,"varun","varun1@g","1335577890","varu","varu",ad);
        Optional<Donor> dr =Optional.of(d);
		when(donorDao.findById(101)).thenReturn(dr);
        assertThrows(DuplicateDonorException.class, ()->donorService.registerDonor(d));
    }
	
	
	
    @Test
	public void wrongCredentialsExceptionTest() throws SQLException
	{
		
		Address ad = new Address(1011,"hyd","telangana","1234567891","kkt");
		 Donor d = new Donor(101,"varun","varun1@g","1335577890","varu","varu",ad);;
         Optional<Donor> dr = Optional.of(d);
         String username="varu";
		when(donorDao.findByUsername(username)).thenReturn(dr);
        when(donorDao.login("varu")).thenReturn("varu");
		assertThrows(WrongCredentialsException.class, ()->donorService.login(username, "varun"));
	}
}

