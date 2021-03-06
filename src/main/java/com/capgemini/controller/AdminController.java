package com.capgemini.controller;

import java.io.Console;

//imports

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchDonorException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.DonationDistribution;
import com.capgemini.model.DonationDistributionStatus;
import com.capgemini.model.Donor;
import com.capgemini.model.Employee;
import com.capgemini.service.AdminService;

//Admin Controller class

@CrossOrigin(origins = "http://localhost:4242")
@RestController
@RequestMapping(path = "/admin")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	//Add Employee
	@PostMapping(value="/employee/add",consumes = "application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws DuplicateEmployeeException
	{
		if(adminService.addEmployee(employee))
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//modifying employee
	@PutMapping(value="/employee/update/{empid}",consumes="application/json")
	public ResponseEntity<Employee> modifyEmployee(@PathVariable("empid") int empid,@RequestBody Employee employee) throws NoSuchEmployeeException
	{
		if(adminService.modifyEmployee(employee))
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//deleting employee
	@DeleteMapping(value="/employee/delete/{employeeId}")
	public ResponseEntity<String> removeEmployee(@PathVariable("employeeId")int employeeId) throws NoSuchEmployeeException
	{
		if(adminService.removeEmployee(employeeId))
			return new ResponseEntity<String>("Employee Removed Succesfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee not Removed",HttpStatus.NOT_FOUND);
	}
	
	//Donation Approval
	@GetMapping(value="/approveDonation/{dd_id}")
	public ResponseEntity<DonationDistributionStatus> approveDonation(@PathVariable("dd_id")int donation_distribution_id) {
		System.out.println(donation_distribution_id);
		DonationDistributionStatus status = adminService.approveDonation(donation_distribution_id);
		if(status!=null)
			return new ResponseEntity<DonationDistributionStatus>(status,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	//All Employee list
	@GetMapping(path = "/employee/all",produces = "application/json")
	public ResponseEntity<List<Employee>> findAllEmployee(){
		return new ResponseEntity<List<Employee>>(adminService.findAllEmployee(),HttpStatus.OK);
	}
	
	//Employee with given id
	@GetMapping(path="/employee/getById/{empid}",produces = "application/json")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable(name = "empid")int empid) throws NoSuchEmployeeException{
		return new ResponseEntity<Employee>(adminService.findEmployeeById(empid),HttpStatus.OK);
	}
	
	//Employee with given name
	@GetMapping(path="/employee/getByName/{ename}",produces = "application/json")
	public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable(name = "ename")String ename) throws NoSuchEmployeeException{
		return new ResponseEntity<List<Employee>>(adminService.findEmployeeByName(ename),HttpStatus.OK);
		
	}
	
	@GetMapping(path="/donationDistribution/all",produces = "application/json")
	public ResponseEntity<List<DonationDistribution>> findDonationDistributionByName() {
		return new ResponseEntity<List<DonationDistribution>>(adminService.findAllDonationDistribution(),HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/donor/all",produces = "application/json")
	public ResponseEntity<List<Donor>> findAllDonor(){
		return new ResponseEntity<List<Donor>>(adminService.findAllDonor(),HttpStatus.OK);
	}
	
	@GetMapping(path="/donor/getById/{donorid}",produces = "application/json")
	public ResponseEntity<Donor> findDonorById(@PathVariable(name = "donorid")int donorid) throws NoSuchDonorException{
		return new ResponseEntity<Donor>(adminService.findDonorById(donorid),HttpStatus.OK);
	}
	
}



