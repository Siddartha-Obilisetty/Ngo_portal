package com.capgemini.service;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleService 
{
	public boolean registerNeedyPerson(NeedyPeople person) throws DuplicateNeedyPeopleException;
	public boolean login(NeedyPeople person) throws NoSuchNeedyPeopleException;
	public boolean requestForHelp(int np_id);

}
