package com.capgemini.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	//help needy person
	
	@GetMapping(value="/login")
	public ResponseEntity<HttpStatus> login(@RequestParam String username,@RequestParam String password) throws NoSuchNeedyPeopleException, WrongPasswordException {
		if(employeeService.login(username,password))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping(value="/needypeople/add",produces="application/json")
	public ResponseEntity<HttpStatus> addNeedyPerson(@RequestBody NeedyPeople n) throws DuplicateNeedyPeopleException
	{	
		employeeService.addNeedyPerson(n);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping(value="/needypeople/all",produces="application/json")
	public ResponseEntity<List<NeedyPeople>> getNeedyPeople(){
		return new ResponseEntity<List<NeedyPeople>>(employeeService.findAllNeedyPeople(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/needypeople/getbyId/{np_id}", produces = "application/json")
	public ResponseEntity<NeedyPeople> getNeedyPerson(@PathVariable("np_id") int needyPersonId)throws NoSuchNeedyPeopleException 
	{
		Optional<NeedyPeople> n = employeeService.findNeedyPeopleById(needyPersonId);
		if (n.isPresent())
			return new ResponseEntity<NeedyPeople>(n.get(), HttpStatus.OK);
		else {
			throw new NoSuchNeedyPeopleException(needyPersonId);
			// return new ResponseEntity<Optional<NeedyPeople>>(n,HttpStatus.NO_CONTENT);
		}
	}
	

	@GetMapping(value="/needypeople/getbyName/{np_name}",produces="application/json")
	public ResponseEntity<List<NeedyPeople>>getNeedyPersonByName(@PathVariable("np_name") String np_name) throws NoSuchNeedyPeopleException
	{ 
		List<NeedyPeople> name = null;
		name = employeeService.findNeedyPeopleByName(np_name);
		if(name.size()!=0)
			return new ResponseEntity<List<NeedyPeople>>(name,HttpStatus.OK);
		else
			throw new NoSuchNeedyPeopleException(np_name);
	}
	
	@DeleteMapping(value = "/needypeople/delete/{np_id}", produces = "application/json")
	public ResponseEntity<HttpStatus> deleteNeedyPerson(@PathVariable("np_id") int needyPersonId) throws NoSuchNeedyPeopleException 
	{
		employeeService.removeNeedyPerson(needyPersonId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

}
