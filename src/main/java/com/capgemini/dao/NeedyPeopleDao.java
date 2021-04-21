package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleDao  extends JpaRepository<NeedyPeople, Integer>
{
	public boolean createNeedyPerson(NeedyPeople person);
	public boolean readLoginData(NeedyPeople person);
	public boolean requestForHelp(NeedyPeople person);

}
