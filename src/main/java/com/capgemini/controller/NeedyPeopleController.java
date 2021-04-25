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

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.NeedyPeopleService;

//Needy people Controller class

@RestController
@RequestMapping("/needypeople")
public class NeedyPeopleController 
{
	@Autowired
	NeedyPeopleService needyPeopleService;
	
	public void setNeedyPeopleService(NeedyPeopleService needyPeopleService) {
		this.needyPeopleService = needyPeopleService;
	}

	//register
	@PostMapping(value="/register",consumes ="application/json")
    public ResponseEntity<HttpStatus> registerNeedyPerson(@RequestBody NeedyPeople person)  throws DuplicateNeedyPeopleException
    {
        if(needyPeopleService.registerNeedyPerson(person))
        	return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
	
	//login
	@GetMapping(value="/login")
	public ResponseEntity<HttpStatus> login(@RequestParam String username,@RequestParam String password) throws NoSuchNeedyPeopleException, WrongCredentialsException {
		if(needyPeopleService.login(username, password))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	//request for help
	@PutMapping(value="/request",consumes="application/json")
	public ResponseEntity<HttpStatus> requestForHelp(@RequestParam int np_id) {
		if(needyPeopleService.requestForHelp(np_id))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}
	
	
}
