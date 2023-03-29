package com.antino.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.antino.entity.Employee;

import com.antino.service.EmployeeService;
import com.antino.util.Response;

@RestController
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public Response addEmployee(@Valid @RequestBody Employee employee) {
		System.out.println("Employee is saved successfully"+employee.toString());
		try {
			Employee savedEmployee = this.employeeService.addEmployee(employee);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Employee Details saved successfully");
			response.setResponse(savedEmployee);
			return response;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
		}
	
	}
	
	@GetMapping("/totalEmployees")
	public Response getAllEmployees(){
		try {
			
			List<Employee> employeeList = employeeService.getAllEmployees();
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Employee Details fetched successfully!");
			response.setResponse(employeeList); 
			return response;
			
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
			
		}
	}
	
	
	
	@RequestMapping(value = "/pagingAndSortingEmployee/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public Response employeePagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
		try {
			
			Page<Employee> employeeList = employeeService.getEmployeePagination(pageNumber, pageSize);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Employee Details fetched successfully!");
			response.setResponse(employeeList); 
			return response;
			
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
			
		}
		
	}

}
