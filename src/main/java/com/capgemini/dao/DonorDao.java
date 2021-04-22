package com.capgemini.dao;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Donation;
import com.capgemini.model.Donor;

public interface DonorDao extends JpaRepository<Donor, Integer>
{
	@Modifying
	@Query(value="insert into donor values(:#{#donor})",nativeQuery = true)
	public boolean createDonor(@Param("donor")Donor donor) throws SQLException;
	
	//public boolean login(Donor donor) throws SQLException;
	
	@Query(value="select d from Donor d where donor_username=?1")
	public Donor findDonorByDonorUsername(String username);
	
	//public Donation donateToNGO(Donation donation);
	
	@Query(value="select donor_password from donor where donor_username=:username",nativeQuery = true)
	public String forgotPassword(@Param("username")String username);
	
	@Modifying
	@Query(value="update donor set donor_password=:newPassword where donor_username=:username",nativeQuery = true)
	public String resetPassword(@Param("username")String username,@Param("newPassword")String newPassword);

}
