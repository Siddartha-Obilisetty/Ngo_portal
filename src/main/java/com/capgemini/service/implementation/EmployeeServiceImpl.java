package com.capgemini.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	@Override
	public String helpNeedyPerson(DonationDistribution distribute) {
		return null;
	}
	//not finished
	@Transactional
	@Override
	public boolean login(Employee employee) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		return false;
	}	

	@Transactional
	@Override
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException {
		NeedyPeople np = employee.readNeedyPeopleById(person.getNp_id());
		if(np!=null) {
			return employee.createNeedyPerson(person);
		}
		else {
			throw new DuplicateNeedyPeopleException(person.getNp_id());
		}
		
	}
	
	@Transactional
	@Override
	public boolean removeNeedyPerson(NeedyPeople person) throws NoSuchNeedyPeopleException {
		NeedyPeople np = employee.readNeedyPeopleById(person.getNp_id());
		if(np!=null) {
			return employee.deleteNeedyPerson(person.getNp_id());
		}
		else {
			throw new NoSuchNeedyPeopleException(person.getNp_id());
		}
	}

	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	@Override
	public List<NeedyPeople> findAllNeedyPeople() {
		return employee.readAllNeedyPeople();
	}

}
