package com.capgemini.service.implementation;

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
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	EmployeeDao employeeDao;
	
	

	@Override
	@Transactional
	public String helpNeedyPerson(int np_id) {
		DonationDistribution dd = employeeDao.getDonationDistritionByNp_id(np_id);
		if(dd.getStatus()==DonationDistributionStatus.APPROVED)
		{
			dd.setDod(LocalDate.now());
			
			return "Approved and Amount Deducted";
		}
		return "rejected";
	}
	
	

	@Transactional
	@Override
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongPasswordException {
		Optional<Employee> e = employeeDao.findByUsername(username);
		if(e.isPresent()) {
			Employee emp = e.get();
			System.out.println(password+" "+employeeDao.login(username));
			if(password.equals(employeeDao.login(username)))
				return true;
			else
				throw new WrongPasswordException();
		}
		else {
			throw new NoSuchNeedyPeopleException(username);
		}
		
	}
	
	@Override
	@Transactional
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		Optional<NeedyPeople> np=employeeDao.readNeedyPeopleById(person.getNp_id());
		if(np.isPresent()) {
			throw new DuplicateNeedyPeopleException(person.getNp_id());
		}
		else {
			addAddress(person.getAddress());
			try {
				employeeDao.createNeedyPerson(person.getNp_id(), person.getNp_name(), person.getPhone(), person.getFamily_income(),person.getUsername(),person.getPassword(),person.getDonationType(), person.getAddress().getAdd_Id());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return true;
		}
	}
	
	@Override
	@Transactional
	public boolean removeNeedyPerson(int id) throws NoSuchNeedyPeopleException
	{
		try {
			Optional<NeedyPeople> np = employeeDao.readNeedyPeopleById(id);
			if(np.isPresent()) {
				employeeDao.deleteNeedyPerson(id);
				employeeDao.deleteAddress(np.get().getAddress().getAdd_Id());
				return true;
			}
			else {
				throw new NoSuchNeedyPeopleException(id);
			}
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		  }
		return false;
		
	}
	

	@Override
	@Transactional(readOnly = true)
	public Optional<NeedyPeople> findNeedyPeopleById(int id) throws NoSuchNeedyPeopleException 
	{
		return employeeDao.readNeedyPeopleById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws NoSuchNeedyPeopleException
	{
		return employeeDao.readNeedyPeopleByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NeedyPeople> findAllNeedyPeople() 
	{
		return employeeDao.readAllNeedyPeople();
	}

	@Override
	@Transactional
	public void addAddress(Address a) {
		System.out.println("in address method");
		try {
			employeeDao.addAddress(a.getAdd_Id(), a.getCity(), a.getState(), a.getPin(), a.getLandmark());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}

	@Override
	@Transactional
	public boolean removeAddress(int add_Id) {
	try {
		employeeDao.deleteAddress(add_Id);
	} 
	catch (SQLException e) {
		System.out.println(e.getMessage());
		return false;
	}
	return true;	
	}



	
}
