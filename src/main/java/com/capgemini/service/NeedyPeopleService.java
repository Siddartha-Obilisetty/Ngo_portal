package com.capgemini.service;

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
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongCredentialsException;
	public Optional<NeedyPeople> getByUsername(String username);

}
