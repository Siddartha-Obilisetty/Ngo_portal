package com.capgemini.service;

//imports

import java.util.List;
import java.util.Optional;

import com.capgemini.exception.*;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

//Service interface

public interface EmployeeService 
{
	//methods to be implemented
	public Optional<Employee> login(String username, String password) throws NoSuchEmployeeException, WrongCredentialsException;
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException;
	public boolean removeNeedyPerson(int needyPersonId) throws NoSuchNeedyPeopleException;
	public NeedyPeople findNeedyPeopleById(int id) throws NoSuchNeedyPeopleException;
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws NoSuchNeedyPeopleException;
	public List<NeedyPeople> findAllNeedyPeople();
	public String helpNeedyPerson(int empid, int np_id);
	public boolean removeAddress(int add_Id);
	public boolean addAddress(Address a);

}
