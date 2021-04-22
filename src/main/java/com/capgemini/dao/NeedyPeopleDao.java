package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleDao  extends JpaRepository<NeedyPeople, Integer>
{
	@Modifying
	@Query(value="insert into needypeople values(:#{#person})",nativeQuery=true)
	public boolean createNeedyPerson(NeedyPeople person);
	//public boolean readLoginData(NeedyPeople person);
	//public boolean requestForHelp(NeedyPeople person);

}
