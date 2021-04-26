package com.capgemini.service.implementation;

//imports

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.NeedyPeopleDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.DonationItem;
import com.capgemini.model.DonationType;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.NeedyPeopleService;

//Service implementation class

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService
{
	@Autowired
	NeedyPeopleDao needyPeople;

	public void setNeedyPeople(NeedyPeopleDao needyPeople) {
		this.needyPeople = needyPeople;
	}
	
	//registering needy person
	@Transactional
	@Override
	public boolean registerNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		Optional<NeedyPeople> np = needyPeople.findById(person.getNeedyPeopleId());
		if(np.isPresent()) {
			throw new DuplicateNeedyPeopleException(person.getNeedyPeopleId());
		}
		try {
			if(needyPeople.addAddress(person.getAddress())!=0)
				if(needyPeople.createNeedyPerson(person)!=0)
					return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	//login
	@Transactional(readOnly = true)
	@Override
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongCredentialsException 
	{
		Optional<NeedyPeople> n = getByUsername(username);
		if(n.isPresent()) {
			if(password.equals(needyPeople.readLoginData(username)))
				return true;
			else
				throw new WrongCredentialsException();
		}
		else {
			throw new NoSuchNeedyPeopleException(username);
		}		
	}
	
	//request for help
	@Transactional
	@Override
	public boolean requestForHelp(int np_id) 
	{
		NeedyPeople np=needyPeople.findById(np_id).get();
		np.setRequest(1);
		
		DonationItem di = new DonationItem();
		di.setItemId(1);
		di.setType(DonationType.MONEY);
		di.setItemDescription("money");
		
		DonationDistribution dd = new DonationDistribution();
		//dd.setDistributionId(1);
		dd.setNeedyPeople(np);
		dd.setAmountDistributed(500);
		dd.setStatus(DonationDistributionStatus.PENDING);
		dd.setDonationItem(di);
		dd.setEmployee(needyPeople.getEmployeeById(501).get());
		if(needyPeople.addDonationItem(di)!=0)
			if(needyPeople.addDonationDistribution(dd)!=0)
				if(needyPeople.requestForHelp(np.getRequest(), np_id)!=0)
					return true;
		return false;
	}

	//finding needy people by user name
	@Transactional(readOnly = true)
	@Override
	public Optional<NeedyPeople> getByUsername(String username) {
		return needyPeople.getByUsername(username);
	}

}
