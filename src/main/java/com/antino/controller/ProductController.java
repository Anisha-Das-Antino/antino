package com.antino.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.antino.entity.Customer;
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
		System.out.println("Product is saved" + product.toString());
		try {
			Product savedProduct = this.productService.createProduct(product);

			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product saved successfully");
			response.setResponse(savedProduct);
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

	@GetMapping("/product/pageNO/{pageNo}/noOfItems/{noOfItems}")
	public Response getAllProduct(@PathVariable int pageNo, @PathVariable int noOfItems) {
		try {
			List<Product> productList = productService.getAllProduct(pageNo, noOfItems);

			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product details fetched successfully!");
			response.setResponse(productList);
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

	@GetMapping("/allProducts")
	public Response getAllProducts() {
		try {

			List<Product> productList = productService.getAllProducts();

			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product details fetched successfully!");
			response.setResponse(productList);
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

	@PutMapping("/products/{productId}/quantity/add/{quantity}")
	public Response addQuantity(@PathVariable Integer productId, @PathVariable Integer quantity) {

		try {
			Product productQuantity = productService.addQuantity(productId, quantity);

			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product details fetched successfully!");
			response.setResponse(productQuantity);
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

	@PutMapping("/products/{productId}/quantity/subtract/{quantity}")
	public Response subtractQuantity(@PathVariable Integer productId, @PathVariable Integer quantity) {

		try {
			Product productQuantity = productService.subtractQuantity(productId, quantity);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product details fetched successfully!");
			response.setResponse(productQuantity);
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
	
	@PutMapping("/products/{productId}")
    public Response updateProductQuantity(@PathVariable Integer productId, @RequestParam int quantity) {
        
        try {
			Product productQuantity = productService.updateProductQuantity(productId, quantity);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product details fetched successfully!");
			response.setResponse(productQuantity);
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
	
	@GetMapping("/products/search")
    public Response searchBooks(@RequestParam(required = false) String productName, @RequestParam(required = false) String category) {
        
        try {
        	List<Product> productSearch = productService.searchProduct(productName, category);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Product searched successfully!");
			response.setResponse(productSearch);
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

//    @PostMapping("/products/{productId}/rent")
//    public Response rentBook(@PathVariable Integer productId, @RequestParam Integer userId, @RequestParam Integer quantity, @RequestParam LocalDate issueDate, @RequestParam LocalDate returnDate) {
//        
//        try {
//        	Product productRent = productService.rentProduct(productId, userId, quantity, issueDate, returnDate);
//			
//			Response response = new Response();
//			response.setStatusCode(200);
//			response.setMessage("Product rented successfully!");
//			response.setResponse(productRent);
//			return response;
//
//		} catch (Exception ex) {
//
//			ex.printStackTrace();
//			Response response = new Response();
//			response.setStatusCode(500);
//			response.setMessage("Internal Server Error");
//			response.setResponse(null);
//			return response;
//
//		}
//    }
//
//    @PostMapping("/products/{productId}/return")
//    public Response returnBook(@PathVariable Integer productId, @RequestParam Integer userId, @RequestParam Integer quantity, @RequestParam LocalDate returnDate) {
//        
//        try {
//        	Product productReturn = productService.returnProduct(productId, userId, quantity, returnDate);
//			
//			Response response = new Response();
//			response.setStatusCode(200);
//			response.setMessage("Product returned successfully!");
//			response.setResponse(productReturn);
//			return response;
//
//		} catch (Exception ex) {
//
//			ex.printStackTrace();
//			Response response = new Response();
//			response.setStatusCode(500);
//			response.setMessage("Internal Server Error");
//			response.setResponse(null);
//			return response;
//
//		}
//    }

//	@GetMapping("/product/{category}")
//	public List<Product> getAllProductOfSpecificType(@PathVariable String category) {
//		return ((PostService) postService).getPostById(id);
//	}
	
//	@PutMapping("products/{productId}")
//    public ResponseEntity<Product> updateProductDetails(@PathVariable Integer productId,
//                                                           @RequestBody Product productUpdateRequest) {
//        try {
//            Product product = productService.updateProductDetails(productId, productUpdateRequest);
//            return ResponseEntity.ok(customer);
//        } catch (ResponseStatusException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
//        }
//    }
//
//	@DeleteMapping("/customers/{customerId}")
//	public ResponseEntity<String> deleteCustomer (@PathVariable Integer customerId) {
//		try {
//            customerService.deleteCustomer(customerId);
//            return ResponseEntity.ok("Customer with ID " + customerId + " deleted successfully");
//        } catch (ResponseStatusException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
//        }
//    }


}
