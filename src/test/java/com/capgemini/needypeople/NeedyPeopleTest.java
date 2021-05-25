package com.capgemini.needypeople;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.NeedyPeopleDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationType;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.NeedyPeopleService;

@SpringBootTest
public class NeedyPeopleTest 
{
	@Autowired
	NeedyPeopleService needyPeopleService;
	
	@MockBean
	NeedyPeopleDao needyPeopleDao;
	
	@Test
	public void saveNeedyPersonTest() throws DuplicateNeedyPeopleException 
	{
		
			DonationType dt = DonationType.MONEY;
			Address ad = new Address(1011,"hyd","tel","676767","gmr complex");
			NeedyPeople np = new NeedyPeople(111,"sid","8888", 20000.0, "siddu", "uddis",0,ad);
			when(needyPeopleDao.save(np)).thenReturn(np);
			assertEquals(true,needyPeopleService.registerNeedyPerson(np));

	}
	@Test
	public void getByUsernameTest()
	{
		String username="sravani";
		DonationType dt = DonationType.MONEY;
		Address ad = new Address(1021,"hydedqd","tegal","6767","gmr compl");
		when(needyPeopleDao.getByUsername(username)).thenReturn(Optional.of(new NeedyPeople(123,"var","6666",200000.0,"varun","nurav",0, ad)));
		assertEquals(true,needyPeopleService.getByUsername(username).isPresent());
	}
	
	@Test
	public void loginTest() throws NoSuchNeedyPeopleException, WrongCredentialsException
	{
		String username="madhu";
		String password="sid";
		when(needyPeopleDao.readLoginData(username)).thenReturn(password);
		assertEquals(false,needyPeopleService.login(username, password));
	}
	
		
	

}
