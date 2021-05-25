package com.capgemini.service;

import java.util.List;
import java.util.Optional;

//imports

import com.capgemini.exception.*;
import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;

//Service interface
public interface DonorService 
{
	//methods to be implemented
	public boolean registerDonor(Donor donor) throws DuplicateDonorException;
	public Optional<Donor> login(String username, String password) throws WrongCredentialsException, NoSuchDonorException;
	public boolean donateToNGO(Donation donation);
	public void sendThankyouMailToDonator(String email);
	public String forgotPassword(String username) throws WrongCredentialsException;
	public String resetPassword(String username, String oldPassword, String newPassword) throws WrongCredentialsException;
	public void emailPasswordToDonor(String email,String password);
	public boolean addAddress(Address a);
	public Optional<List<Donation>> getDonationByDonorId(int donorId);
}
