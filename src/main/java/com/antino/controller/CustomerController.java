package com.antino.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.antino.entity.Customer;
import com.antino.service.CustomerService;
import com.antino.util.Response;

@Controller
@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/pageNo/{pageNo}/noOfCustomers/{noOfCustomers}")
	public List<Customer> getAllCustomer(@PathVariable int pageNo, @PathVariable int noOfCustomers) {
		List<Customer> customerList = customerService.getAllCustomer(pageNo,noOfCustomers);
		
		return customerList;
	}
	
	@PostMapping("/addcustomer")
	public Response addCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("Customer is added"+customer.toString());
		try {
			Customer savedCustomer = this.customerService.addCustomer(customer);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Customer added successfully");
			response.setResponse(savedCustomer);
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

	@RequestMapping(value = "/pagingAndSortingCustomer/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public Page<Customer> customerPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
		return customerService.getCustomerPagination(pageNumber, pageSize);
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}

}