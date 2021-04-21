package com.capgemini.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	EmployeeDao employee;

	//not finished
	@Override
	public String helpNeedyPerson(DonationDistribution distribute) {
		return null;
	}
	//not finished
	@Override
	public boolean login(Employee employee) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		NeedyPeople np = employee.readNeedyPeopleById(person.getNeedyPersonid());
		if(np!=null) {
			return employee.createNeedyPerson(person);
		}
		else {
			throw new DuplicateNeedyPeopleException(person.getNeedyPersonid());
		}
		
	}

	@Override
	public boolean removeNeedyPerson(NeedyPeople person) throws NoSuchNeedyPeopleException {
		NeedyPeople np = employee.readNeedyPeopleById(person.getNeedyPersonid());
		if(np!=null) {
			return employee.deleteNeedyPerson(person);
		}
		else {
			throw new NoSuchNeedyPeopleException(person.getNeedyPersonid());
		}
	}

	@Override
	public NeedyPeople findNeedyPeopleById(int id) throws NoSuchNeedyPeopleException {
		NeedyPeople np = employee.readNeedyPeopleById(id);
		if(np!=null) {
			return np;
		}
		else {
			throw new NoSuchNeedyPeopleException(id);
		}
	}

	@Override
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws NoSuchNeedyPeopleException {
		List<NeedyPeople> np = employee.readNeedyPeopleByName(name);
		if(np!=null) {
			return np;
		}
		else {
			throw new NoSuchNeedyPeopleException(name);
		}
	}

	@Override
	public List<NeedyPeople> findAllNeedyPeople() {
		return employee.readAllNeedyPeople();
	}

}
