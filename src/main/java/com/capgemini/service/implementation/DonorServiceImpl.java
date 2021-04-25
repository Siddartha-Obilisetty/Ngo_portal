package com.capgemini.service.implementation;

//imports

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DonorDao;
import com.capgemini.exception.DuplicateDonorException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.DonorService;

//Service implementation class

@Service
public class DonorServiceImpl implements DonorService
{
	@Autowired
	DonorDao donorDao;
	
	public void setDonorDao(DonorDao donorDao) {
		this.donorDao = donorDao;
	}

	//registering donor
	@Transactional
	@Override
	public boolean registerDonor(Donor donor) throws DuplicateDonorException {
		try {
			Optional<Donor> d = donorDao.findByUsername(donor.getUsername());
			if(d.isPresent()) {
				throw new DuplicateDonorException(donor.getDonorId());
			}
			else {
				int i=0;
				if(addAddress(donor.getAddress()))
					i=donorDao.createDonor(donor);
					if(i!=0)
						return true;
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
	//adding address
	@Transactional
	public boolean addAddress(Address a)
	{
		//admin.addAddress(a);
		try {
			int i=donorDao.addAddress(a);
			if(i!=0)
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	//login
	@Transactional
	@Override
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongCredentialsException {
		Optional<Donor> d = donorDao.findByUsername(username);
		if(d.isPresent()) {
			Donor donor=d.get();
			if(donor.getPassword().equals(donorDao.login(username)))
				return true;
			else
				throw new WrongCredentialsException();
		}
		else {
			throw new NoSuchNeedyPeopleException(username);
		}
		
	}
	
	//forgot password
	@Transactional(readOnly = true)
	@Override
	public String forgotPassword(String username) throws WrongCredentialsException {
		Optional<Donor> d=donorDao.findByUsername(username);
		if(d.isEmpty()) {
			throw new WrongCredentialsException(); 
		}
		emailPasswordToDonor(d.get().getEmail(),donorDao.forgotPassword(username));
		return "Your password has been sent to "+d.get().getEmail();
	}

	//reset password
	@Transactional
	@Override
	public String resetPassword(String username,String oldPassword,String newPassword) throws WrongCredentialsException {
		Optional<Donor> d=donorDao.findByUsername(username);
		if(d.isEmpty())
			throw new WrongCredentialsException();
		if(d.get().getPassword().equals(oldPassword))
		{
			donorDao.resetPassword(username,newPassword);
			return "Your password has been Changed";
		}
		else {
			return "Wrong Password";
		}
	}	

	//donating to ngo
	@Transactional
	@Override
	public boolean donateToNGO(Donation donation) {
		donorDao.addDonationItem(donation.getItem());
		donorDao.addDonation(donation);
		sendThankyouMailToDonator(donation.getDonor().getEmail());
		int i=donorDao.donateToNGO(donation);
		if(i!=0)
			return true;
		return false;
	}
	
	//sending thank you mail
	@Transactional(readOnly = true)
	@Override
	public void sendThankyouMailToDonator(String email) {
		System.out.println("Thank you mail sent to "+email);
	}

	//sending password to email 
	@Transactional(readOnly = true)
	@Override
	public void emailPasswordToDonor(String email,String password) {
		System.out.println("Your credentials has been sent to "+email);
	}

}
