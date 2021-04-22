package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface EmployeeDao extends JpaRepository<Employee, Integer>
{
	//public boolean login(Employee employee) throws SQLException;
	
	@Modifying
	@Query(value="insert into needypeople values(:#{#person})",nativeQuery=true)
	public boolean createNeedyPerson(@Param("person") NeedyPeople person);
	
	@Modifying
	@Query(value="delete from needypeople wher np_id=?1",nativeQuery = true)
	public boolean deleteNeedyPerson(int needyPersonId);
	
	@Query(value="select p from NeedyPeople p where np_id=?1")
	public NeedyPeople readNeedyPeopleById(int id);
	
	@Query(value="select p from NeedyPeople p where np_name=?1")
	public List<NeedyPeople> readNeedyPeopleByName(String name);
	
	@Query(value="select p from NeedyPeople p")
	public List<NeedyPeople> readAllNeedyPeople();
	
	//public String helpNeedyPerson(DonationDistribution distribute);

}
