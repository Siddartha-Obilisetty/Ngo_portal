package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.DonationDistribution;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface EmployeeDao extends JpaRepository<Employee, Integer>
{
	public boolean login(Employee employee) throws SQLException;
	public boolean createNeedyPerson(NeedyPeople person);
	
	@Modifying
	@Query(value="delete from needypeople where np_id=?1",nativeQuery=true)
	public boolean deleteNeedyPerson(int needyPersonid);
	
	@Query("select p from NeedyPeople p where p.np_id=id")
	public NeedyPeople readNeedyPeopleById(int id);
	
	@Query("select p from NeedyPeople p where p.np_name=name")
	public List<NeedyPeople> readNeedyPeopleByName(String name);
	
	@Query("select p from NeedyPeople p")
	public List<NeedyPeople> readAllNeedyPeople();
	
	public String helpNeedyPerson(DonationDistribution distribute);

}
