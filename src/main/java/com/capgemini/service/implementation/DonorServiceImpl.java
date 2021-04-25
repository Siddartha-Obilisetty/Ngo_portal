package com.capgemini.service.implementation;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DonorDao;
import com.capgemini.exception.DuplicateDonorException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.DonorService;

@Service
public class DonorServiceImpl implements DonorService
{
	@Autowired
	DonorDao donorDao;
	
	
	@Transactional
	@Override
	public boolean login(String username, String password) throws NoSuchNeedyPeopleException, WrongPasswordException {
		Optional<Donor> d = donorDao.findByUsername(username);
		if(d.isPresent()) {
			Donor donor=d.get();
			if(donor.getPassword().equals(donorDao.login(username)))
				return true;
			else
				throw new WrongPasswordException();
		}
		else {
			throw new NoSuchNeedyPeopleException(username);
		}
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public String forgotPassword(String username) {
		Donor d=donorDao.findDonorByDonorUsername(username);
		emailPasswordToDonor(d.getEmail(),donorDao.forgotPassword(username));
		return "Your password has been sent to "+d.getEmail();
	}

	@Transactional
	@Override
	public String resetPassword(String username,String oldPassword,String newPassword) {
		Donor d=donorDao.findDonorByDonorUsername(username);
		if(d.getPassword().equals(oldPassword))
		{
			donorDao.resetPassword(username,newPassword);
			return "Your password has been Changed";
		}
		else {
			return "Wrong Password";
		}
	}

	@Transactional
	public void addAddress(Address a)
	{
		//admin.addAddress(a);
		try {
			donorDao.addAddress(a.getAddressId(),a.getCity(),a.getState(),a.getPin(),a.getLandmark());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional
	@Override
	public boolean registerDonor(Donor donor) throws DuplicateDonorException {
		try {
			Donor d = donorDao.findDonorByDonorUsername(donor.getUsername());
			if(d!=null) {
				throw new DuplicateDonorException(donor.getDonorId());
			}
			else {
				this.addAddress(donor.getAddress());
				donorDao.createDonor(donor.getDonorId(), donor.getDonorName(), donor.getEmail(), donor.getPhone(), donor.getUsername(), donor.getPassword(), donor.getAddress().getAddressId());
				return true;
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Transactional
	@Override
	public int donateToNGO(Donation donation) {
		donorDao.addDonation(donation.getDonationId(),donation.getDonationAmount(),donation.getItem().getItemId());
		sendThankyouMailToDonator(donation.getDonor());
		return donorDao.donateToNGO(donation);
	}
	
	
	
	@Transactional
	@Override
	public void sendThankyouMailToDonator(Donor donor) {
		System.out.println("Thank you mail sent ");
	}
	

	@Transactional
	@Override
	public void emailPasswordToDonor(String email,String password) {
		System.out.println("Your credentials has been sent to "+email);
	}

}
