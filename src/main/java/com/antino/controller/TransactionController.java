package com.antino.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.antino.entity.Transaction;

import com.antino.service.TransactionService;
import com.antino.util.Response;

@RestController
@CrossOrigin
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/createTransaction")
	public Response createTransaction(@Valid @RequestBody Transaction transaction) {
		System.out.println("Transaction is saved"+transaction.toString());
		try {
			Transaction savedTransaction = this.transactionService.createTransaction(transaction);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Transaction is successfully done");
			response.setResponse(savedTransaction);
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
	
	@GetMapping("/transactions/{customerName}")
	public Response getTransactionByCustomerName(@PathVariable String customerName) {
		
		try {
			List<Transaction> transactionList = transactionService.getTransactionByCustomerName(customerName);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Transaction fetched successfully!");
			response.setResponse(transactionList);
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
	
	@GetMapping("transactions/{customerName}/{productName}")
    public Response getByCustomernameAndProductName(@PathVariable("customerName") String customerName, @PathVariable("productName") String productName){
    
    try {
		List<Transaction> transactionList = transactionService.getByCustomernameAndProductName(customerName, productName);
		
		Response response = new Response();
		response.setStatusCode(200);
		response.setMessage("Transaction fetched successfully!");
		response.setResponse(transactionList);
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
	
	@GetMapping("/transaction/pageNo/{pageNo}/noOfTransactions/{noOfTransactions}/sortBy/{sortBy}")
	public Response getAllTransaction(@PathVariable int pageNo, @PathVariable int noOfTransactions, @PathVariable String sortBy) {
		try {
			List<Transaction> transactionList = transactionService.getAllTransaction(pageNo,noOfTransactions,sortBy);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Transaction fetched successfully!");
			response.setResponse(transactionList);
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

	
	@GetMapping("/transactions")
	public Response getAllTransactions(){
		
		try {
			List<Transaction> transactionList = transactionService.getAllTransactions();
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Transaction fetched successfully!");
			response.setResponse(transactionList);
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
	
	@GetMapping("/transactions/current-month/sales")
	public Response getSaleCurrentMonth(){
		
		try {
			Map<String,Object> transactionList = transactionService.getSaleCurrentMonth();
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Transaction fetched successfully!");
			response.setResponse(transactionList);
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
