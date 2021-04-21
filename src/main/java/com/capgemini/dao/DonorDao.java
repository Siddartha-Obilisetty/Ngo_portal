package com.capgemini.dao;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.Donation;
import com.capgemini.model.Donor;

public interface DonorDao extends JpaRepository<Donor, Integer>
{
	public boolean createDonor(Donor donor) throws SQLException;
	public boolean login(Donor donor) throws SQLException;
	
	@Query("select d from Donor d where donor_username=?1")
	public Donor findDonorByDonorUsername(String username);
	
	public Donation donateToNGO(Donation donation);
	
	public String forgotPassword(String username);
	
	public String resetPassword(String username);

}
