package com.antino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
		
		Pageable pageable = PageRequest.of(pageNo, noOfItems);
		Page<Product>  listOfProductPage = productRepository.findAll(pageable);
		List<Product> products = new ArrayList<Product>();
		if(listOfProductPage != null && listOfProductPage.hasContent()) {
			products = listOfProductPage.getContent();
		}
		return products;
	}

	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
		
	}

	public Product addQuantity(Integer productId, int quantity) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
        product.setQuantity(product.getQuantity() + quantity);
        return productRepository.save(product);
	}

	public Product subtractQuantity(Integer productId, int quantity) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
        if (product.getQuantity() < quantity) {
        	throw new RuntimeException("Not enough or insufficient quantity left..");
        }
        product.setQuantity(product.getQuantity() - quantity);
        return productRepository.save(product);
	}

//	public Product updateProductQuantity(Integer productId, int quantity) {
//		Optional<Product> productOptional = productRepository.findById(productId);
//        if (productOptional.isPresent()) {
//            Product product = productOptional.get();
//            int newQuantity = product.getQuantity() + quantity;
//            product.setQuantity(newQuantity);
//            return productRepository.save(product);
//        } else {
//            throw new EntityNotFoundException("Product not found with ID: " + productId);
//        }
//	}
	
	public List<Product> searchProduct(String productName, String category) {
        return productRepository.findByProductNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(productName, category);
    }

//    public Product rentProduct(Integer productId, Integer userId, Integer quantity, LocalDate issueDate, LocalDate returnDate) {
//    	Optional<Product> productOptional = productRepository.findById(productId);
//        if (productOptional != null && productOptional.isPresent()) {
//            Product product = productOptional.get();
//            int newQuantity = product.getQuantity() - quantity;
//            product.setQuantity(newQuantity);
//            return productRepository.save(product);
//        } else {
//            throw new EntityNotFoundException("Product with ID " + productId + " is not available for rental.");
//        }
//    }
//
//    public Product returnProduct(Integer productId, Integer userId, Integer quantity, LocalDate returnDate) {
//    	Optional<Product> productOptional = productRepository.findById(productId);
//        if (productOptional != null && productOptional.isPresent()) {
//            Product product = productOptional.get();
//            int newQuantity = product.getQuantity() + quantity;
//            product.setQuantity(newQuantity);
//            return productRepository.save(product);
//        } else {
//            throw new EntityNotFoundException("Product with ID " + productId + " is not available.");
//        }
//    }

	public Product getProductById(Integer productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
	}

	public Product updateProductDetails(Integer productId, Product productUpdateRequest) {
		Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (productUpdateRequest.getProductName() != null) {
                product.setProductName(productUpdateRequest.getProductName());
            }
            if (productUpdateRequest.getProductDescription() != null) {
                product.setProductDescription(productUpdateRequest.getProductDescription());
            }
            if (productUpdateRequest.getCategory() != null) {
                product.setCategory(productUpdateRequest.getCategory());
            }
            if (productUpdateRequest.getPrice() != null) {
                product.setPrice(productUpdateRequest.getPrice());
            }
            if (productUpdateRequest.getGst() != null) {
                product.setGst(productUpdateRequest.getGst());
            }
            if (productUpdateRequest.getVendor() != null) {
                product.setVendor(productUpdateRequest.getVendor());
            }
            if (productUpdateRequest.getPurchaseType() != null) {
                product.setPurchaseType(productUpdateRequest.getPurchaseType());
            }
            
            productRepository.save(product);
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
	}

	public void deleteProduct(Integer productId) {
		Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
		
	}
}
