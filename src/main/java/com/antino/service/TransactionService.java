package com.antino.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.antino.dto.DashBoardGraphSaleDTO;
import com.antino.entity.Transaction;
import com.antino.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction createTransaction(@Valid Transaction transaction) {

		System.out.println("Inside Transaction Service Package" + transaction.toString());
		transaction.setCreatedAt(new Date());

		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAllTransaction(int pageNo, int noOfTransactions, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, noOfTransactions, Sort.Direction.DESC, ("createdAt"));
		Page<Transaction> listOfTransactionPage = transactionRepository.findAll(pageable);
		List<Transaction> transactions = new ArrayList<Transaction>();
		if (listOfTransactionPage != null && listOfTransactionPage.hasContent()) {
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

	public Map<String,Object> getSaleCurrentMonth() {
		// TODO Auto-generated method stub
		List<Transaction> transactions = transactionRepository.getAllOfCurrentMonth();
		List<DashBoardGraphSaleDTO> listOfDashBoardGraphSaleDTO = new ArrayList<>();
		Map<Integer, Double> hashMapTotalSale = new HashMap<>();
		
		Map<String,Object> response = new HashMap<>();

		for (Transaction transaction : transactions) {
			System.out.println("transaction " + transaction.toString());

			Calendar c = Calendar.getInstance();
			c.setTime(transaction.getCreatedAt());
			int day = c.get(Calendar.DAY_OF_MONTH);

			Double currentSales = hashMapTotalSale.get(day);
			if (currentSales != null) {
				currentSales = currentSales + transaction.getTotalAmount();
				hashMapTotalSale.put(day, currentSales);
				System.out.println("if hasmap day " + day);
				System.out.println("if hasmap sales " + currentSales);
			} else {
				hashMapTotalSale.put(day,  transaction.getTotalAmount());
				System.out.println("hasmap day " + day);
				System.out.println("hasmap sales " + currentSales);
			}
		}
		Set<Integer> days = hashMapTotalSale.keySet();

		System.out.println("size " + days.size());

		for (Integer day : days) {
			System.out.println("day " + day);
			DashBoardGraphSaleDTO dashBoardGraphSaleDTO = new DashBoardGraphSaleDTO();
			dashBoardGraphSaleDTO.setDate(day);
			dashBoardGraphSaleDTO.setSales(hashMapTotalSale.get(day));
			listOfDashBoardGraphSaleDTO.add(dashBoardGraphSaleDTO);
		}
		
		response.put("graph", listOfDashBoardGraphSaleDTO);
		response.put("size", transactions.size());
		response.put("transactionDetails", transactions);

		return response;
	}

}
