package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Employee;
import com.capgemini.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	@GetMapping(path = "/employee/all")
	public ResponseEntity<List<Employee>> findAllEmployee(){
		return new ResponseEntity<List<Employee>>(adminService.findAllEmployee(),HttpStatus.OK);
	}
	/*@GetMapping(path="/employee/{empid}")
	public ResponseEntity<Employee> findEmployeeByid(@PathVariable(name = "empid")int empid){
		
	}*/
}
