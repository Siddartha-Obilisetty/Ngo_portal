package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

//imports

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.DuplicateDonorException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.Donation;
import com.capgemini.model.Donor;
import com.capgemini.service.DonorService;

//Donor Controller class
@CrossOrigin(origins = "http://localhost:4242")
@RestController
@RequestMapping("/donor")
public class DonorController 
{
	@Autowired
	DonorService donorService;

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}
	
	//register
	@PostMapping(value="/register",consumes = "application/json")
	public ResponseEntity<HttpStatus> registerDonor(@RequestBody Donor donor) throws DuplicateDonorException
	{
		if(donorService.registerDonor(donor))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
	//login
	@GetMapping(value="/login")
	public ResponseEntity<Donor> login(@RequestParam String username,@RequestParam String password) throws NoSuchDonorException, WrongCredentialsException {
		Optional<Donor> d= donorService.login(username, password);
		if(d.isPresent())
			return new ResponseEntity<Donor>(d.get(),HttpStatus.OK);
		else 
        	return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Donate to NGO
	@PutMapping(value="/donate")
	public ResponseEntity<String> donateToNGO(@RequestBody Donation donation) {
		if(donorService.donateToNGO(donation))
		{
			String s="Thank You!!";
			return new ResponseEntity<String>(s,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/donations/all/{donorId}")
	public ResponseEntity<List<Donation>> getDonationByDonorId(@PathVariable("donorId") int donorId){
		Optional<List<Donation>> donations = donorService.getDonationByDonorId(donorId);
		if(donations.isPresent()) {
			return new ResponseEntity<List<Donation>>(donations.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	//Reset password
	@PutMapping(value="/reset_password")
	public ResponseEntity<HttpStatus> resetPassword(@RequestParam String username,@RequestParam String oldPassword,@RequestParam String newPassword) throws WrongCredentialsException
	{
		String message=donorService.resetPassword(username,oldPassword,newPassword);
		if(message!=null)
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
	}
	
	//Forgot password
	@GetMapping(value="/forgot_password",produces = "application/json")
	public ResponseEntity<HttpStatus> forgotPassword(@RequestParam String username) throws WrongCredentialsException
	{
		String message=donorService.forgotPassword(username);
		if(message!=null)
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
