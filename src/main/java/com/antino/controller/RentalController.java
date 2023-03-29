package com.antino.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antino.entity.Rental;
import com.antino.service.RentalService;
import com.antino.util.Request;
import com.antino.util.Response;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public Response createRental(@RequestBody Request rentalRequest) {
        
        try {
        	Rental rental = rentalService.createRental(rentalRequest.getProductId(), rentalRequest.getCustomerId(), rentalRequest.getQuantity(), rentalRequest.getIssueDate(), rentalRequest.getReturnDate());

			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Rental details requested successfully!");
			response.setResponse(rental);
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null);
			return response;
		}
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Integer rentalId) {
        Rental rental = rentalService.getRentalById(rentalId);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

}

