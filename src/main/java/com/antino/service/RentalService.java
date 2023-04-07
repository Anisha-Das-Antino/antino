package com.antino.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.antino.entity.MyUser;
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
    private final UserService userService;
	
	@Autowired
	private final ProductRepository productRepository;

    public RentalService(RentalRepository rentalRepository, ProductService productService, UserService userService, ProductRepository productRepository) {
        this.rentalRepository = rentalRepository;
        this.productService = productService;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    public Rental createRental(Integer productId, Integer userId, int quantity, LocalDate issueDate, LocalDate returnDate, String rentalStatus) {
        Product product = productService.getProductById(productId);
        MyUser user = userService.getUserById(userId);

        Rental rental = new Rental();
        rental.setProductId(productId);
        rental.setUserId(userId);
        rental.setQuantity(quantity);
        rental.setIssueDate(issueDate);
        rental.setReturnDate(returnDate);
		rental.setRentalStatus(rentalStatus);
        rental.setProduct(product);
        rental.setMyUser(user);
      
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

	public List<Rental> getAllRentals() {
		return rentalRepository.findAll();
	}

	public List<Rental> getRentalsById(Integer userId) {
		
		return rentalRepository.findByUserId(userId);
		
	}


}
