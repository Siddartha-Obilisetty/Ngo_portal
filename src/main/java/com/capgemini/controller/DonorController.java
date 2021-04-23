package com.capgemini.controller;

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
import com.capgemini.model.Donor;
import com.capgemini.service.DonorService;

@RestController
@RequestMapping("/donor")
public class DonorController 
{
	@Autowired
	DonorService donorService;

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}
	
	//donate to ngo,login
	
	@PostMapping(value="/register",consumes = "application/json")
	public ResponseEntity<HttpStatus> registerDonor(@RequestBody Donor donor) throws DuplicateDonorException
	{
		donorService.registerDonor(donor);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping(value="/reset_password",consumes = "application/json")
	public ResponseEntity<String> resetPassword(@RequestParam String username,@RequestParam String oldPassword,@RequestParam String newPassword)
	{
		String message=donorService.resetPassword(username,oldPassword,newPassword);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping(value="/forgot_password",produces = "application/json")
	public ResponseEntity<String> forgotPassword(@RequestParam String username)
	{
		String message=donorService.forgotPassword(username);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
