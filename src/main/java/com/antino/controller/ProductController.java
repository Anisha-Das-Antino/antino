package com.antino.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.antino.entity.Product;
import com.antino.service.ProductService;
import com.antino.util.Response;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addproduct")
	public Response createProduct(@Valid @RequestBody Product product) {
		System.out.println("Product is saved"+product.toString());
		try {
			Product savedProduct = this.productService.createProduct(product);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product saved successfully");
			response.setResponse(savedProduct);
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
	
	@GetMapping("/product/pageNO/{pageNo}/noOfItems/{noOfItems}")
	public List<Product> getAllProduct(@PathVariable int pageNo, @PathVariable int noOfItems) {
		List<Product> productList = productService.getAllProduct(pageNo,noOfItems);
		
		return productList;
	}
	
//	@GetMapping("/product/{category}")
//	public List<Product> getAllProductOfSpecificType(@PathVariable String category) {
//		return ((PostService) postService).getPostById(id);
//	}

}
