package com.antino.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antino.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public List<Customer> findBycustomerEmail(String customerEmail);
	
	public List<Customer> findBycustomerName(String customerName);
	
	public List<Customer> findBycustomerPhone(Integer customerPhone);
	
	Customer findByCustomerName(String customerName);

	Page<Customer> findAll(Pageable pageable);

}
