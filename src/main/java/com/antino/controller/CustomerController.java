package com.antino.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.antino.entity.Customer;
import com.antino.service.CustomerService;
import com.antino.util.Response;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/pageNo/{pageNo}/noOfCustomers/{noOfCustomers}")
	public Response getAllCustomer(@PathVariable int pageNo, @PathVariable int noOfCustomers) {
		try {
			List<Customer> customerList = customerService.getAllCustomer(pageNo,noOfCustomers);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Customer details fetched successfully!");
			response.setResponse(customerList); 
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
	
	@PostMapping("/addcustomer")
	public Response addCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("Customer is added"+customer.toString());
		try {
			Customer savedCustomer = this.customerService.addCustomer(customer);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Customer added successfully!");
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
    public Response customerPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
		try {
			
			Page<Customer> customerList = customerService.getCustomerPagination(pageNumber, pageSize);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Customer details fetched successfully!");
			response.setResponse(customerList); 
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
	
	@GetMapping("/customers")
	public Response getAllCustomers(){
		try {
			
			List<Customer> customerList = customerService.getAllCustomers();
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Customer details fetched successfully!");
			response.setResponse(customerList); 
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
	
	@PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable Integer customerId,
                                                           @RequestBody Customer customerUpdateRequest) {
        try {
            Customer customer = customerService.updateCustomerDetails(customerId, customerUpdateRequest);
            return ResponseEntity.ok(customer);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer (@PathVariable Integer customerId) {
		try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok("Customer with ID " + customerId + " deleted successfully");
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

}