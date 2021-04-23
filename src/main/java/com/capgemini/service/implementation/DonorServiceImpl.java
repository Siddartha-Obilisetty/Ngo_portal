package com.capgemini.service.implementation;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DonorDao;
import com.capgemini.exception.DuplicateDonorException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.model.Address;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;
import com.capgemini.service.DonorService;

@Service
public class DonorServiceImpl implements DonorService
{
	@Autowired
	DonorDao donorDao;
	
	
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
		if(d.getPassword()==oldPassword)
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
			donorDao.addAddress(a.getAdd_Id(),a.getCity(),a.getState(),a.getPin(),a.getLandmark());
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
				throw new DuplicateDonorException(donor.getDonor_id());
			}
			else {
				this.addAddress(d.getAddress());
				donorDao.createDonor(d.getDonor_id(), d.getDonor_name(), d.getEmail(), d.getPhone(), d.getUsername(), d.getPassword(), d.getAddress().getAdd_Id());
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
	public boolean login(Donor donor) throws NoSuchDonorException {
		//try {
			Optional<Donor> d = donorDao.findById(donor.getDonor_id());
			if(d.isPresent()) {
				return true;/*donorDao.login(donor)*/	
			}
			else {
				throw new NoSuchDonorException(donor.getDonor_id());
			}
		/*}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}*/
	}

	@Transactional
	@Override
	public Donation donateToNGO(Donation donation) {
		// TODO Auto-generated method stub
		return null;
	}

	/*not finished
	@Transactional
	@Override
	public Donation donateToNGO(Donation donation) {
		sendThankyouMailToDonator(donation.getDonor());
		return donorDao.donateToNGO(donation);
	}*/
	
	
	
	@Transactional
	@Override
	public void sendThankyouMailToDonator(Donor donor) {
		System.out.println("Thank you mail sent");
	}
	

	@Transactional
	@Override
	public void emailPasswordToDonor(String email,String password) {
		System.out.println("Your credentials has been sent to "+email);
	}

}
