package com.capgemini.service;

import java.util.List;

//imports

import java.util.Optional;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.NeedyPeople;

//Service interface

public interface NeedyPeopleService 
{
	//methods to be implemented
	public boolean registerNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException;
	public boolean requestForHelp(int np_id);
	public Optional<NeedyPeople> login(String username, String password) throws NoSuchNeedyPeopleException, WrongCredentialsException;
	public Optional<NeedyPeople> getByUsername(String username);
	public int getDistributionId();
}
