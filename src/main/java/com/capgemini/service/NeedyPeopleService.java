package com.capgemini.service;

import java.util.Optional;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleService 
{
	public boolean registerNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException;
	public boolean requestForHelp(int np_id);
	public String login(String username, String password) throws NoSuchNeedyPeopleException, WrongPasswordException;
	public Optional<NeedyPeople> getByUsername(String username);

}
