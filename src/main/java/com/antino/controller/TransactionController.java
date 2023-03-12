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
	public List<Transaction> getTransactionByCustomerName(@PathVariable String customerName) {
		return transactionService.getTransactionByCustomerName(customerName);
	}
	
	@GetMapping("transactions/{customerName}/{productName}")
    public List<Transaction> getByCustomernameAndProductName(@PathVariable("customerName") String customerName, @PathVariable("productName") String productName){
    return transactionService.getByCustomernameAndProductName(customerName, productName);
}
	
	@GetMapping("/transaction/pageNo/{pageNo}/noOfTransactions/{noOfTransactions}/sortBy/{sortBy}")
	public List<Transaction> getAllTransaction(@PathVariable int pageNo, @PathVariable int noOfTransactions, @PathVariable String sortBy) {
		List<Transaction> transactionList = transactionService.getAllTransaction(pageNo,noOfTransactions,sortBy);
		
		return transactionList;
	}
	
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
	
	@GetMapping("/transactions/current-month/sales")
	public  Map<String,Object> getSaleCurrentMonth(){
		return transactionService.getSaleCurrentMonth();
	}
	
}
