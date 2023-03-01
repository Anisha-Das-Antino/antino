package com.antino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.antino.entity.Customer;
import com.antino.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/pageNo/{pageNo}/noOfCustomers/{noOfCustomers}")
	public List<Customer> getAllCustomer(@PathVariable int pageNo, @PathVariable int noOfCustomers) {
		List<Customer> customerList = customerService.getAllCustomer(pageNo,noOfCustomers);
		
		return customerList;
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}

}