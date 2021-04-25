package com.capgemini.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationType;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface EmployeeDao extends JpaRepository<Employee, Integer>
{
	public Optional<Employee> findByUsername(String usernme);
	
	@Query(value="select e.password from Employee e where e.username=?1")
	public String login(String username);
		
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:add_Id,:city,:state,:pin,:landmark)",nativeQuery = true)
	public int addAddress(@Param("add_Id")int add_Id,@Param("city")String city,@Param("state")String state,@Param("pin")String pin,@Param("landmark")String landmark)throws SQLException;

	@Modifying
	@Query(value="insert into Needy_People (np_id,np_name,phone,family_income,username,password,donation_Type,add_Id) values(:np_id,:np_name,:phone,:family_income,:username,:password,:donationType,:add_id)",nativeQuery = true)
	public int createNeedyPerson(@Param("np_id")int np_id,@Param("np_name")String np_name,@Param("phone")String phone,@Param("family_income")double family_income,@Param("username")String username,@Param("password") String password,@Param("donationType") DonationType donationType, @Param("add_id")int add_id)throws SQLException;
	@Modifying
	@Query(value="delete Address a where a.add_Id=:add_Id",nativeQuery = true)
	
	public int deleteAddress(@Param("add_Id")int add_Id)throws SQLException;
	@Modifying
	@Query(value="delete NeedyPeople n where n.np_id=:np_id")
	public int deleteNeedyPerson(@Param("np_id")int needyPersonId)throws SQLException;

	@Query(value="select p from NeedyPeople p where np_id=:npid")
	public Optional<NeedyPeople> readNeedyPeopleById(@Param("npid")int id);
	
	@Query(value="select p from NeedyPeople p where np_name=?1")
	public List<NeedyPeople> readNeedyPeopleByName(String name);
	
	@Query(value="select p from NeedyPeople p")
	public List<NeedyPeople> readAllNeedyPeople();

	@Query(value="select d from Donation_Distribution d where d.np_id=?1",nativeQuery = true)
	public DonationDistribution getDonationDistritionByNp_id(int np_id);
	
	@Modifying
	@Query("update DonationDistribution dd set dd.dateOfDistribution=:#{#distribute.getDateOfDistribution()}")
	public String helpNeedyPerson(DonationDistribution distribute);

}

	//public boolean login(Employee employee) throws SQLException;
	//public String helpNeedyPerson(DonationDistribution distribute);


