package com.antino.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antino.entity.Customer;
import com.antino.entity.Product;
import com.antino.entity.Rental;
import com.antino.repository.RentalRepository;

@Service
public class RentalService {
	
	@Autowired
	private final RentalRepository rentalRepository;
	
	@Autowired
    private final ProductService productService;
	
	@Autowired
    private final CustomerService customerService;

    public RentalService(RentalRepository rentalRepository, ProductService productService, CustomerService customerService) {
        this.rentalRepository = rentalRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public Rental createRental(Integer productId, Integer customerId, int quantity, LocalDate issueDate, LocalDate returnDate) {
        Product product = productService.getProductById(productId);
        Customer customer = customerService.getCustomerById(customerId);

        Rental rental = new Rental();
        rental.setProductId(productId);
        rental.setCustomerId(customerId);
        rental.setQuantity(quantity);
        rental.setIssueDate(issueDate);
        rental.setReturnDate(returnDate);
        rental.setProduct(product);
        rental.setCustomer(customer);
      
        return rentalRepository.save(rental);
    }

    public Rental getRentalById(Integer rentalId) {
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        return optionalRental.orElse(null);
    }

}
