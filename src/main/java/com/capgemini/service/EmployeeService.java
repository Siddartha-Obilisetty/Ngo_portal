package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface EmployeeService 
{
	public boolean login(Employee employee)throws NoSuchEmployeeException;
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException;
	public boolean removeNeedyPerson(NeedyPeople person) throws NoSuchNeedyPeopleException;
	public NeedyPeople findNeedyPeopleById(int id) throws NoSuchNeedyPeopleException;
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws NoSuchNeedyPeopleException;
	public List<NeedyPeople> findAllNeedyPeople();
	public String helpNeedyPerson(DonationDistribution distribute);

}
