package com.antino.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antino.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public Page<Product> findAll(Pageable pageable);
	
}
