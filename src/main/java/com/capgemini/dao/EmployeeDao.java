package com.capgemini.dao;

//imports

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationType;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

//Repository class

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>
{
	//creating NeedyPeople	
	@Modifying
	@Query(value="insert into Needy_People (needy_people_id,needy_people_name,phone,family_income,username,password,request,address_id)"+""
			+ "values(:#{#np.getNeedyPeopleId()},:#{#np.getNeedyPeopleName()},:#{#np.getPhone()},:#{#np.getFamilyIncome()},"+
			":#{#np.getUsername()},:#{#np.getPassword()},:#{#np.getRequest()},:#{#np.getAddress().getAddressId()})",nativeQuery = true)
	public int createNeedyPerson(@Param("np")NeedyPeople np)throws SQLException;
		
	//deleting needy people
	@Modifying
	@Query(value="delete NeedyPeople n where n.needyPeopleId=?1")
	public int deleteNeedyPerson(int needyPersonId)throws SQLException;

	
	//adding address
	@Modifying
	@Query(value="insert into Address (address_id,city,state,pin,landmark) values(:#{#add.getAddressId()},"+
			":#{#add.getCity()},:#{#add.getState()},:#{#add.getPin()},:#{#add.getLandmark()})",nativeQuery = true)
	public int addAddress(@Param("add")Address address)throws SQLException;
		
	//deleting address
	@Modifying
	@Query(value="delete Address a where a.addressId=?1")
	public int deleteAddress(int add_id)throws SQLException;
	
	//login
	@Query(value="select e.password from Employee e where e.username=?1")
	public String login(String username);
	
	//updating date of distribution in DonationDistribution table
	@Modifying
	@Query("update DonationDistribution dd set dd.dateOfDistribution=:#{#distribute.getDateOfDistribution()},dd.employee=:#{#distribute.getEmployee()}")
	public int helpNeedyPerson(@Param("distribute")DonationDistribution distribute);

	//updating donation box with the donation amount
	@Modifying
	@Query(value="update DonationBox d set d.totalMoneyCollection=d.totalMoneyCollection-:amt")
	public int deductAmountAfterApproval(@Param("amt")double amount);
	
	
	//extracting DonationDistrition data using Needy person id
	@Query(value="select d from DonationDistribution d where d.needyPeople.needyPeopleId=:np_id")
	public List<DonationDistribution> getDonationDistritionByNp_id(@Param("np_id")int np_id);
	
	//Extracting Employee data using user name
	@Query(value="select e from Employee e where e.username=?1")
	public Optional<Employee> findByUsername(String usernme);
	
	//Extracting NeedyPeople data using id
	@Query(value="select p from NeedyPeople p where needyPeopleId=:npid")
	public Optional<NeedyPeople> readNeedyPeopleById(@Param("npid")int id);
	
	//Extracting NeedyPeople data using id
	@Query(value="select p from NeedyPeople p where p.needyPeopleName=?1")
	public List<NeedyPeople> readNeedyPeopleByName(String name);
	
	//Extracting all NeedyPeople data
	@Query(value="select p from NeedyPeople p")
	public List<NeedyPeople> readAllNeedyPeople();

}
