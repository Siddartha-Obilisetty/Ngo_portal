package com.capgemini.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.DonationItem;
import com.capgemini.model.DonationType;
import com.capgemini.model.Donor;
import com.capgemini.model.Employee;
import com.capgemini.model.NeedyPeople;

public interface NeedyPeopleDao  extends JpaRepository<NeedyPeople, Integer>
{
	@Query(value="select p from NeedyPeople p where p.np_name=?1")
	public Optional<NeedyPeople> findByNp_name(String np_name);
	/*@Query(value="select n from NeedyPeople n where n.username=:username")
	public NeedyPeople getByUsername(@Param("username")String username);
	*/
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:add_Id,:city,:state,:pin,:landmark)",nativeQuery = true)
	public int addAddress(@Param("add_Id")int add_Id,@Param("city")String city,@Param("state")String state,@Param("pin")String pin,@Param("landmark")String landmark)throws SQLException;
	
	@Modifying
	@Query(value="insert into Needy_People (np_id,np_name,phone,family_income,username,password,donation_Type,add_Id) values (:np_id,:np_name,:phone,:familyincome,:username,:password,:donation_type,:add_id)",nativeQuery = true)
	public int createNeedyPerson(@Param("np_id")int np_id,@Param("np_name")String np_name,@Param("phone")String phone,@Param("familyincome")double familyincome,@Param("username")String username,@Param("password")String password,@Param("donation_type")DonationType donationType,@Param("add_id")int add_id)throws SQLException;
	
	@Modifying
	@Query(value="update NeedyPeople np set np.request=:request where np.np_id=:np_id ",nativeQuery = true)
	public int requestForHelp(@Param("request")int request, @Param("np_id")int np_id);
	
	@Query(value="select p.password from NeedyPeople p where p.username=?1")
	public String readLoginData(String username);

	@Modifying
	@Query(value="insert into Donation_Item (item_id,item_desc,donation_Type) values (?1,?2,?3)",nativeQuery = true)
	public int addDonationItem(Long item_id,String item_desc,DonationType donationType);
	
	@Modifying
	@Query(value="insert into Donation_Distribution (distributionid,amt_distributed,status,item_id,np_id,empid) values (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
	public int addDonationDistribution(Long distributionid, double amt_distributed,DonationDistributionStatus status, Long long2, int np_id,int empid);
	

	

}
