package com.capgemini.controller;

//imports

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.DuplicateNeedyPeopleException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.exception.NoSuchNeedyPeopleException;
import com.capgemini.exception.WrongCredentialsException;
import com.capgemini.model.NeedyPeople;
import com.capgemini.service.EmployeeService;

//Employee Controleer class

@RestController
@RequestMapping(value="/employee")
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//login
	@GetMapping(value="/login")
	public ResponseEntity<HttpStatus> login(@RequestParam String username,@RequestParam String password) throws WrongCredentialsException, NoSuchEmployeeException {
		if(employeeService.login(username,password))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Add Needy People
	@PostMapping(value="/needypeople/add",produces="application/json")
	public ResponseEntity<HttpStatus> addNeedyPerson(@RequestBody NeedyPeople n) throws DuplicateNeedyPeopleException
	{	
		if(employeeService.addNeedyPerson(n))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
	//Deleting Needy People
	@DeleteMapping(value = "/needypeople/delete/{np_id}", produces = "application/json")
	public ResponseEntity<HttpStatus> deleteNeedyPerson(@PathVariable("np_id") int needyPersonId) throws NoSuchNeedyPeopleException 
	{
		if(employeeService.removeNeedyPerson(needyPersonId))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else 
        	return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
	//Help Needy People
	@PutMapping(value="/helpNeedyPeople/{dd_id}")
	public ResponseEntity<String> helpNeedyPerson(@PathVariable("dd_id")int dd_id) {
		String s=employeeService.helpNeedyPerson(dd_id);
		if(s!=null)
			return new ResponseEntity<String>(s,HttpStatus.OK);
		else
			return new ResponseEntity<String>(s,HttpStatus.NO_CONTENT);
	}
	
	//All Needy People
	@GetMapping(value="/needypeople/all",produces="application/json")
	public ResponseEntity<List<NeedyPeople>> getNeedyPeople(){
		return new ResponseEntity<List<NeedyPeople>>(employeeService.findAllNeedyPeople(),HttpStatus.OK);
	}
	
	//NeedyPeople by Id
	@GetMapping(value = "/needypeople/getbyId/{np_id}", produces = "application/json")
	public ResponseEntity<NeedyPeople> getNeedyPerson(@PathVariable("np_id") int needyPersonId)throws NoSuchNeedyPeopleException 
	{
		return new ResponseEntity<NeedyPeople>(employeeService.findNeedyPeopleById(needyPersonId),HttpStatus.OK);
	}
	
	//NeedyPeople by Name
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
	

}
