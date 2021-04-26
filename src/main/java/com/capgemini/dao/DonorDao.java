package com.capgemini.dao;

//imports

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.DonationItem;
import com.capgemini.model.Donor;

//Repository Class

@Repository
public interface DonorDao extends JpaRepository<Donor, Integer>
{
	//creating donor
	@Modifying
	@Query(value="insert into Donor (donor_id,donor_name,email,phone,username,password,address_id) values"+
			"(:#{#donor.getDonorId()},:#{#donor.getDonorName()},:#{#donor.getEmail()},:#{#donor.getPhone()},"+
			":#{#donor.getUsername()},:#{#donor.getPassword()},:#{#donor.getAddress()})",nativeQuery = true)
	public int createDonor(@Param("donor")Donor donor) throws SQLException;
	
	//adding address
	@Modifying
	@Query(value="insert into Address (address_id,city,state,pin,landmark) values(:#{#add.getAddressId()},"
			+ ":#{#add.getCity()},:#{#add.getState()},:#{#add.getPin()},:#{#add.getLandmark()})",nativeQuery = true)
	public int addAddress(@Param("add")Address address)throws SQLException;
	
	//login
	@Query(value="select d.password from Donor d where d.username=?1")
	public String login(String username);
	
	//updating old password with new password	
	@Modifying
	@Query(value="update Donor d set d.password=:newPassword where d.username=:username")
	public int resetPassword(@Param("username")String username,@Param("newPassword")String newPassword);

	//Retrieving old password
	@Query(value="select d.password from Donor d where d.username=:username")
	public String forgotPassword(@Param("username")String username);
	
	//updating donation box with the donation amount
	@Modifying
	@Query(value="update DonationBox d set d.totalMoneyCollection=d.totalMoneyCollection+:#{#donation.getDonationAmount()}")
	public int donateToNGO(@Param("donation")Donation donation);

	
	//adding donation data to donation table
	@Modifying
	@Query(value="insert into Donation (donation_Id,donation_Amount,donation_Date,item_id,donor_id) values"+
			"(:#{#d.getDonationId()},:#{#d.getDonationAmount()},:#{#d.getDonationDate()},"
			+":#{#d.getItem().getItemId()},:#{#d.getDonor().getDonorId()})",nativeQuery = true)
	public int addDonation(@Param("d")Donation donation);
	

	//adding data to DonationItem table
	@Modifying
	@Query(value="insert into Donation_Item (item_id,item_description,type) values (:#{#item.getItemId()},"+
			":#{#item.getItemDescription()},:#{#item.getType()})",nativeQuery = true)
	public int addDonationItem(@Param("item")DonationItem item);
	
	
	//Extracting Donor data using user name
	@Query(value="select d from Donor d where d.username=?1")
	public Optional<Donor> findByUsername(String username);
	
}
