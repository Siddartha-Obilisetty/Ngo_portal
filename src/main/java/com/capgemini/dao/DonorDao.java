package com.capgemini.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Donation;
import com.capgemini.model.DonationItem;
import com.capgemini.model.Donor;

public interface DonorDao extends JpaRepository<Donor, Integer>
{
	public Optional<Donor> findByUsername(String username);
	
	@Modifying
	@Query(value="update Donation_Box d set d.total_money_collection=d.total_money_collection+:#{#donation.getDonation_amount()}",nativeQuery = true)
	public int donateToNGO(Donation donation);

	@Query(value="select d.password from Donor d where d.username=?1")
	public String login(String username);
	
	
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:add_Id,:city,:state,:pin,:landmark)",nativeQuery = true)
	public int addAddress(@Param("add_Id")int add_Id,@Param("city")String city,@Param("state")String state,@Param("pin")String pin,@Param("landmark")String landmark)throws SQLException;
	
	@Modifying
	@Query(value="insert into Donor (donor_id,donor_name,email,phone,username,password,add_Id) values(:donor_id,:donor_name,:email,:phone,:username,:password,:add_id)",nativeQuery = true)
	public int createDonor(@Param("donor_id")int donor_id,@Param("donor_name")String donor_name,@Param("email")String email,@Param("phone")String phone,@Param("username")String username,@Param("password")String password,@Param("add_id")int add_id) throws SQLException;
	
	@Modifying
	@Query(value="update Donor d set d.password=:newPassword where d.username=:username")
	public int resetPassword(@Param("username")String username,@Param("newPassword")String newPassword);


	@Query(value="select d.password from Donor d where d.username=:username")
	public String forgotPassword(@Param("username")String username);
	
	
	@Query(value="select d from Donor d where d.username=?1")
	public Donor findDonorByDonorUsername(String username);

	@Query(value="insert into Donation (donation_id,donation_amount,item_id) values(?1,?2,?3)",nativeQuery = true)
	public int addDonation(int donation_id, double donation_amount, Long item_id);
	
	
}
