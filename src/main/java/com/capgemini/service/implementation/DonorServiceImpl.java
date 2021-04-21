package com.capgemini.service.implementation;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.DonorDao;
import com.capgemini.exception.DuplicateDonorException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;
import com.capgemini.service.DonorService;

@Service
public class DonorServiceImpl implements DonorService
{
	@Autowired
	DonorDao donorDao;

	//doubt

	@Override
	public void sendThankyouMailToDonator(Donor donor) {
		System.out.println("Thank you mail sent");
	}
	
	//doubt

	@Override
	public String forgotPassword(String username) {
		Donor d=donorDao.findDonorByDonorUsername(username);
		emailPasswordToDonor(d.getDonorEmail());
		return "Your password has been sent to "+d.getDonorEmail();
	}

	//arg change
	@Override
	public String resetPassword(String username,String oldPassword,String newPassword) {
		Donor d=donorDao.findDonorByDonorUsername(username);
		if(d.getDonorPassword()==oldPassword)
		{
			d.setDonorPassword(newPassword);
			return "Your password has been Changed";
		}
		else {
			return "Wrong Password";
		}
	}

	//doubt
	@Override
	public void emailPasswordToDonor(String email) {
		System.out.println("Your credentials has been sent to "+email);
	}

	
	

	
	@Override
	public boolean registerDonor(Donor donor) throws DuplicateDonorException {
		try {
			Donor d = donorDao.findDonorByDonorUsername(donor.getDonorUsername());
			if(d!=null) {
				throw new DuplicateDonorException(donor.getDonorId());
			}
			else {
				donorDao.createDonor(donor);
				return true;
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean login(Donor donor) throws NoSuchDonorException {
		try {
			Optional<Donor> d = donorDao.findById(donor.getDonorId());
			if(d.isPresent()) {
				return donorDao.login(donor);	
			}
			else {
				throw new NoSuchDonorException(donor.getDonorId());
			}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Override
	public Donation donateToNGO(Donation donation) {
		sendThankyouMailToDonator(donation.getDonor());
		return donorDao.donateToNGO(donation);
	}

}
