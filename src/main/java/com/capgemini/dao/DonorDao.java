package com.capgemini.dao;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Donation;
import com.capgemini.model.Donor;

public interface DonorDao extends JpaRepository<Donor, Integer>
{

	//public Donation donateToNGO(Donation donation);

	//public boolean login(Donor donor) throws SQLException;
	
	
	@Modifying
	@Query(value="insert into Address (add_Id,city,state,pin,landmark) values(:add_Id,:city,:state,:pin,:landmark)",nativeQuery = true)
	public void addAddress(@Param("add_Id")int add_Id,@Param("city")String city,@Param("state")String state,@Param("pin")String pin,@Param("landmark")String landmark)throws SQLException;
	
	@Modifying
	@Query(value="insert into Donor (donor_id,donor_name,email,phone,username,password,add_Id) values(:donor_id,:donor_name,:email,:phone,:username,:password,:add_id)",nativeQuery = true)
	public boolean createDonor(@Param("donor_id")int donor_id,@Param("donor_name")String donor_name,@Param("email")String email,@Param("phone")String phone,@Param("username")String username,@Param("password")String password,@Param("add_id")int add_id) throws SQLException;
	
	@Modifying
	@Query(value="update Donor d set d.password=:newPassword where d.username=:username")
	public String resetPassword(@Param("username")String username,@Param("newPassword")String newPassword);


	@Query(value="select d.password from Donor d where d.username=:username")
	public String forgotPassword(@Param("username")String username);
	
	
	@Query(value="select d from Donor d where donor_username=?1")
	public Donor findDonorByDonorUsername(String username);
	
	
}
