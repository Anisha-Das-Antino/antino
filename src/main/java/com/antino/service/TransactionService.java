package com.antino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.antino.entity.Transaction;
import com.antino.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction createTransaction(@Valid Transaction transaction) {
		
		System.out.println("Inside Transaction Service Package"+transaction.toString());
		transaction.setCreatedAt(new Date());
		
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAllTransaction(int pageNo, int noOfTransactions, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, noOfTransactions,Sort.Direction.DESC,("createdAt"));
		Page<Transaction>  listOfTransactionPage = transactionRepository.findAll(pageable);
		List<Transaction> transactions = new ArrayList<Transaction>();
		if(listOfTransactionPage != null && listOfTransactionPage.hasContent()) {
			transactions = listOfTransactionPage.getContent();
		}
		
		return transactions;
	}

	public List<Transaction> getAllTransactions() {
		
		return transactionRepository.findAll();
	}

	public List<Transaction> getTransactionByCustomerName(String customerName) {
		return transactionRepository.findByCustomerName(customerName);
	}

	public List<Transaction> getByCustomernameAndProductName(String customerName, String productName) {
		return transactionRepository.findByCustomerNameAndProductName(customerName, productName);
	}

}
