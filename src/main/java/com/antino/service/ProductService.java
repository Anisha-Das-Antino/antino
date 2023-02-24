package com.antino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antino.entity.Product;
import com.antino.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(Product product) {
		System.out.println("Inside Product Service Package"+product.toString());
		product.setCreatedAt(new Date());
		
		return productRepository.save(product);
	}

	public List<Product> getAllProduct(int pageNo, int noOfItems) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, noOfItems);
		Page<Product>  listOfProductPage = productRepository.findAll(pageable);
		List<Product> products = new ArrayList<Product>();
		if(listOfProductPage != null && listOfProductPage.hasContent()) {
			products = listOfProductPage.getContent();
		}
		return products;
	}

}
