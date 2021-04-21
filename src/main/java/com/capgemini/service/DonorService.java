package com.capgemini.service;

import com.capgemini.exception.*;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;

public interface DonorService 
{
	public boolean registerDonor(Donor donor) throws DuplicateDonorException;
	public boolean login(Donor donor) throws NoSuchDonorException;
	public Donation donateToNGO(Donation donation);
	public void sendThankyouMailToDonator(Donor donor);
	public String forgotPassword(String username);
	public String resetPassword(String username, String oldPassword, String newPassword);
	public void emailPasswordToDonor(String email);
	
	

}
