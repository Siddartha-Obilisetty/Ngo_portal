package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface EmployeeService 
{
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongPasswordException;
	public boolean addNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException;
	public boolean removeNeedyPerson(int needyPersonId) throws NoSuchNeedyPeopleException;
	public Optional<NeedyPeople> findNeedyPeopleById(int id) throws NoSuchNeedyPeopleException;
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws NoSuchNeedyPeopleException;
	public List<NeedyPeople> findAllNeedyPeople();
	public String helpNeedyPerson(int np_id);
	boolean removeAddress(int add_Id);
	void addAddress(Address a);

}
