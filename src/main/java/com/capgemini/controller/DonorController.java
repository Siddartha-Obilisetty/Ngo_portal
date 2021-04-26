package com.capgemini.controller;

//imports

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<HttpStatus> login(@RequestParam String username,@RequestParam String password) throws NoSuchDonorException, WrongCredentialsException {
		if(donorService.login(username,password))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
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
	
	//Reset password
	@PutMapping(value="/reset_password",consumes = "application/json")
	public ResponseEntity<String> resetPassword(@RequestParam String username,@RequestParam String oldPassword,@RequestParam String newPassword) throws WrongCredentialsException
	{
		String message=donorService.resetPassword(username,oldPassword,newPassword);
		if(message!=null)
			return new ResponseEntity<String>(message,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//Forgot password
	@GetMapping(value="/forgot_password",produces = "application/json")
	public ResponseEntity<String> forgotPassword(@RequestParam String username) throws WrongCredentialsException
	{
		String message=donorService.forgotPassword(username);
		if(message!=null)
			return new ResponseEntity<String>(message,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
