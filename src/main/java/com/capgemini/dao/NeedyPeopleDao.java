package com.capgemini.dao;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleDao  extends JpaRepository<NeedyPeople, Integer>
{
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:add_Id,:city,:state,:pin,:landmark)",nativeQuery = true)
	@Transactional
	public int addAddress(@Param("add_Id")int add_Id,@Param("city")String city,@Param("state")String state,@Param("pin")String pin,@Param("landmark")String landmark)throws SQLException;
	
	@Modifying
	@Query(value="insert into NeedyPeople (np_id,np_name,phone,familyincome,add_Id) values (:np_id,:np_name,:phone,:familyincome,:add_id)",nativeQuery = true)
	@Transactional
	public int createNeedyPerson(@Param("np_id")int np_id,@Param("np_name")String np_name,@Param("phone")String phone,@Param("familyincome")double familyincome,@Param("add_id")int add_id)throws SQLException;
	//public boolean readLoginData(NeedyPeople person);
	//public boolean requestForHelp(NeedyPeople person);

}
