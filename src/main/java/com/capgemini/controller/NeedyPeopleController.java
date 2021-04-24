package com.capgemini.controller;

import java.util.Optional;

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
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.NeedyPeopleService;

@RestController
@RequestMapping("/needypeople")
public class NeedyPeopleController 
{
	@Autowired
	NeedyPeopleService needyPeopleService;
	
	@GetMapping(value="/login")
	public ResponseEntity<HttpStatus> login(@RequestParam String username,@RequestParam String password) throws NoSuchNeedyPeopleException, WrongPasswordException {
		Optional<NeedyPeople> n = needyPeopleService.getByUsername(username);
		System.out.println(n.get().getNp_id());
		if(n.isPresent()) {
			if(n.get().getPassword().equals(needyPeopleService.login(username,password)))
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			else
				return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		else {
			throw new NoSuchNeedyPeopleException(username);
		}
	}
	
	@PostMapping(value="/register",consumes ="application/json")
    public ResponseEntity<HttpStatus> registerNeedyPerson(@RequestBody NeedyPeople person)  throws DuplicateNeedyPeopleException
    {
        if(needyPeopleService.registerNeedyPerson(person))
        	return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
	
	@PutMapping(value="/request",consumes="application/json")
	public ResponseEntity<HttpStatus> requestForHelp(@RequestParam int np_id) {
		if(needyPeopleService.requestForHelp(np_id))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	
}
