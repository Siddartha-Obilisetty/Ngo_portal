package com.capgemini.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Address;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.DonationItem;
import com.capgemini.model.DonationType;
import com.capgemini.model.Donor;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleDao  extends JpaRepository<NeedyPeople, Integer>
{
	
	//creating NeedyPeople	
	@Modifying
	@Query(value="insert into Needy_People (needy_people_id,needy_people_name,phone,family_income,username,password,request,address_id)"+""
			+ "values(:#{#np.getNeedyPeopleId()},:#{#np.getNeedyPeopleName()},:#{#np.getPhone()},:#{#np.getFamilyIncome()},"+
			":#{#np.getUsername()},:#{#np.getPassword()},:#{#np.getRequest()},:#{#np.getAddress().getAddressId()})",nativeQuery = true)
	public int createNeedyPerson(@Param("np")NeedyPeople np)throws SQLException;
	
	//adding address
	@Modifying
	@Query(value="insert into Address (address_id,city,state,pin,landmark) values(:#{#add.getAddressId()},"+
			":#{#add.getCity()},:#{#add.getState()},:#{#add.getPin()},:#{#add.getLandmark()})",nativeQuery = true)
	public int addAddress(@Param("add")Address address)throws SQLException;
		
	//login
	@Query(value="select p.password from NeedyPeople p where p.username=?1")
	public String readLoginData(String username);

	//updating NeedyPeople setting request flag
	@Modifying
	@Query(value="update NeedyPeople np set np.request=:request where np.needy_people_id=:np_id ",nativeQuery = true)
	public int requestForHelp(@Param("request")int request, @Param("np_id")int np_id);
	
	//adding data into DonationDistribution table
	@Modifying
	@Query(value="insert into Donation_Distribution (distribution_id,amount_distributed,status,item_id,needy_people_id,employee_id)"
			+"values (:#{#dd.getDistributionId()},:#{#dd.getAmountDistributed()},:#{#dd.getStatus()},:#{#dd.getDonationItem().getItemId()},"
			+":#{#dd.getNeedyPeople().getNeedyPeopleId()},:#{#dd.getEmployee().getEmployeeId()})",nativeQuery = true)
	public int addDonationDistribution(@Param("dd")DonationDistribution donationDistribution);
	
	//adding data to DonationItem table
	@Modifying
	@Query(value="insert into Donation_Item (item_id,item_description,type) values (:#{#item.getItemId()},"+
			":#{#item.getItemDescription()},:#{#item.getType()})",nativeQuery = true)
	public int addDonationItem(@Param("item")DonationItem item);
	
	//extracting needy people data using user name 
	@Query(value="select n from NeedyPeople n where n.username=:username")
	public Optional<NeedyPeople> getByUsername(@Param("username")String username);
	
	//extracting employee data using employee id
	@Query(value="select e from Employee e where e.employeeId=?1")
	public Optional<Employee> getEmployeeById(int employeeId);
	
}
