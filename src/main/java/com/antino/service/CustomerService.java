package com.antino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antino.entity.Customer;

import com.antino.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomer(int pageNo, int noOfCustomers) {
		Pageable pageable = PageRequest.of(pageNo, noOfCustomers);
		Page<Customer>  listOfCustomerPage = customerRepository.findAll(pageable);
		List<Customer> customers = new ArrayList<Customer>();
		if(listOfCustomerPage != null && listOfCustomerPage.hasContent()) {
			customers = listOfCustomerPage.getContent();
		}
		return customers;
	}
	
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	public Customer addCustomer(@Valid Customer customer) {
		
		System.out.println("Inside Customer Service Package"+customer.toString());
		customer.setCreatedAt(new Date());
		
		return customerRepository.save(customer);
	}

	public Page<Customer> getCustomerPagination(Integer pageNumber, Integer pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return customerRepository.findAll(pageable);
	}

}
