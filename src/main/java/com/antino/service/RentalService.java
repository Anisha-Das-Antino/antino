package com.antino.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.antino.entity.Customer;
import com.antino.entity.Product;
import com.antino.entity.Rental;
import com.antino.repository.ProductRepository;
import com.antino.repository.RentalRepository;
import com.antino.util.Request.RentalStatus;

@Service
public class RentalService {
	
	@Autowired
	private final RentalRepository rentalRepository;
	
	@Autowired
    private final ProductService productService;
	
	@Autowired
    private final CustomerService customerService;
	
	@Autowired
	private final ProductRepository productRepository;

    public RentalService(RentalRepository rentalRepository, ProductService productService, CustomerService customerService, ProductRepository productRepository) {
        this.rentalRepository = rentalRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.productRepository = productRepository;
    }

    public Rental createRental(Integer productId, Integer customerId, int quantity, LocalDate issueDate, LocalDate returnDate, String rentalStatus) {
        Product product = productService.getProductById(productId);
        Customer customer = customerService.getCustomerById(customerId);

        Rental rental = new Rental();
        rental.setProductId(productId);
        rental.setCustomerId(customerId);
        rental.setQuantity(quantity);
        rental.setIssueDate(issueDate);
        rental.setReturnDate(returnDate);
		rental.setRentalStatus(rentalStatus);
        rental.setProduct(product);
        rental.setCustomer(customer);
      
        return rentalRepository.save(rental);
    }

    public Rental getRentalById(Integer rentalId) {
    	return rentalRepository.findById(rentalId).orElse(null);
    }
    
    public Rental approveRental(Integer rentalId, String rentalStatus) throws Exception {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() -> new NotFoundException());

        if (RentalStatus.valueOf(rentalStatus) == RentalStatus.APPROVED) {
            Product product = rental.getProduct();
            int quantity = product.getQuantity();
            if (quantity == 0) {
                throw new Exception("Product is out of stock");
            }
            product.setQuantity(quantity - 1);
            productRepository.save(product);
        } else if (RentalStatus.valueOf(rentalStatus) == RentalStatus.REJECTED) {
            rental.setRentalStatus(rentalStatus);
            return rentalRepository.save(rental);
        }

        rental.setRentalStatus(rentalStatus);
        return rentalRepository.save(rental);
    }


}
