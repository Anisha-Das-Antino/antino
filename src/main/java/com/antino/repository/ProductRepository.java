package com.antino.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antino.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public Page<Product> findAll(Pageable pageable);
	
	List<Product> findByProductNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String productName, String category);

	public Optional<Product> findByProductId(Integer productId);
	
}
