package com.capgemini.controller;

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

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.NeedyPeopleService;

//Needy people Controller class
@CrossOrigin(origins = "http://localhost:4242")
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
    public ResponseEntity<NeedyPeople> registerNeedyPerson(@RequestBody NeedyPeople person)  throws DuplicateNeedyPeopleException
    {
        if(needyPeopleService.registerNeedyPerson(person))
        	return new ResponseEntity<NeedyPeople>(person,HttpStatus.OK);
        else 
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	//login
	@GetMapping(value="/login")
	public ResponseEntity<NeedyPeople> login(@RequestParam String username,@RequestParam String password) throws NoSuchNeedyPeopleException, WrongCredentialsException {
		Optional<NeedyPeople> np=needyPeopleService.login(username, password);
		if(np.isPresent())
			return new ResponseEntity<NeedyPeople>(np.get(),HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	//request for help
	@PutMapping(value="/request/{npId}")
	public ResponseEntity<HttpStatus> requestForHelp(@PathVariable("npId") int npId) {
		if(needyPeopleService.requestForHelp(npId))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
	}
	
	
}
