package com.antino.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antino.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	Page<Transaction> findAll(Pageable pageable);

	public List<Transaction> findByCustomerName(String customerName);

	@Query(value="SELECT t FROM Transaction t WHERE t.customerName = :customerName and t.productName = :productName")
	List<Transaction> findByCustomerNameAndProductName(@Param("customerName")String customerName, @Param("productName")String productName);

	@Query("select e from Transaction e where year(e.createdAt) = year(current_date) and  month(e.createdAt) = month(current_date)")
	List<Transaction> getAllOfCurrentMonth();
	
}
