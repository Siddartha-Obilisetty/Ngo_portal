package com.capgemini.service.implementation;

//imports

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;

//Service implementation class

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	//login
	@Transactional(readOnly = true)
	@Override
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongCredentialsException {
		Optional<Employee> e = employeeDao.findByUsername(username);
		if(e.isPresent())
			if(password.equals(employeeDao.login(username)))
				return true;
			else
				throw new WrongCredentialsException();
		else 
			throw new NoSuchNeedyPeopleException(username);		
	}

	//adding needy person
	@Override
	@Transactional
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		Optional<NeedyPeople> np=employeeDao.readNeedyPeopleById(person.getNeedyPeopleId());
		if(np.isPresent()) {
			throw new DuplicateNeedyPeopleException(person.getNeedyPeopleId());
		}
		else {
			try {
				int i=0;
				if(addAddress(person.getAddress()))
					i=employeeDao.createNeedyPerson(person);
				if(i!=0)
					return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return false;
		}
	}
	
	//adding address
	@Override
	@Transactional
	public boolean addAddress(Address a) {
		try {
			int i=employeeDao.addAddress(a);
			if(i!=0)
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
		return false;
	}

	//removing needy person
	@Override
	@Transactional
	public boolean removeNeedyPerson(int id) throws NoSuchNeedyPeopleException
	{
		try {
			Optional<NeedyPeople> np = employeeDao.readNeedyPeopleById(id);
			if(np.isPresent()) {
				int i=employeeDao.deleteNeedyPerson(id);
				if(i!=0)
					if(removeAddress(np.get().getAddress().getAddressId()))
						return true;
			}
			else {
				throw new NoSuchNeedyPeopleException(id);
			}
		} 
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
		
	}
	
	//removing address
	@Override
	@Transactional
	public boolean removeAddress(int add_Id) 
	{
		try {
			int i=employeeDao.deleteAddress(add_Id);
			if(i!=0)
				return true;
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//help needy person
	@Override
	@Transactional
	public String helpNeedyPerson(int np_id) {
		DonationDistribution dd = employeeDao.getDonationDistritionByNp_id(np_id);
		if(dd.getStatus().equals(DonationDistributionStatus.APPROVED))
		{
			dd.setDateOfDistribution(LocalDate.now());
			int i=employeeDao.helpNeedyPerson(dd);
			if(i!=0)
				return "Approved and Amount Deducted";
			return "Status not updated";
		}
		else if(dd.getStatus().equals(DonationDistributionStatus.REJECTED))
		{			
			return "Rejected";
		}
		return "Pending";
	}
	
	
	//finding needy people using id
	@Override
	@Transactional(readOnly = true)
	public NeedyPeople findNeedyPeopleById(int id) throws NoSuchNeedyPeopleException 
	{
		Optional<NeedyPeople> np=employeeDao.readNeedyPeopleById(id);
		if(np.isPresent())
			return np.get();
		else
			throw  new NoSuchNeedyPeopleException(id);
	}

	//finding needy people using name
	@Override
	@Transactional(readOnly = true)
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws NoSuchNeedyPeopleException
	{
		return employeeDao.readNeedyPeopleByName(name);
	}

	//finding all needy people
	@Override
	@Transactional(readOnly = true)
	public List<NeedyPeople> findAllNeedyPeople() 
	{
		return employeeDao.readAllNeedyPeople();
	}
	
}
