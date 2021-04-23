package com.capgemini.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.DuplicateEmployeeException;
import com.capgemini.exception.NoSuchEmployeeException;
import com.capgemini.model.Employee;
import com.capgemini.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
	@PostMapping(value="/employee/add",consumes = "application/json")
	public ResponseEntity<HttpStatus> addEmployee(@RequestBody Employee employee) throws DuplicateEmployeeException
	{
		adminService.addEmployee(employee);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}
	
	
	@PutMapping(value="/employee/update",consumes="application/json")
	public ResponseEntity<HttpStatus> modifyEmployee(@RequestBody Employee employee) throws NoSuchEmployeeException
	{
	    adminService.modifyEmployee(employee);
		 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/employee/delete/{employeeId}")
	public ResponseEntity<HttpStatus> removeEmployee(@PathVariable("employeeId")int employeeId) throws NoSuchEmployeeException
	{
		Employee e = adminService.findEmployeeById(employeeId);
		adminService.removeEmployee(employeeId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/employee/all",produces = "application/json")
	public ResponseEntity<List<Employee>> findAllEmployee(){
		return new ResponseEntity<List<Employee>>(adminService.findAllEmployee(),HttpStatus.OK);
	}
	
	@GetMapping(path="/employee/getById/{empid}",produces = "application/json")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable(name = "empid")int empid) throws NoSuchEmployeeException{
		return new ResponseEntity<Employee>(adminService.findEmployeeById(empid),HttpStatus.OK);
	}
	
	@GetMapping(path="/employee/getByName/{ename}",produces = "application/json")
	public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable(name = "ename")String ename) throws NoSuchEmployeeException{
		return new ResponseEntity<List<Employee>>(adminService.findEmployeeByName(ename),HttpStatus.OK);
		
	}
	
}



