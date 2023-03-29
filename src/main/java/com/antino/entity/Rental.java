package com.antino.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Book Rental Details")
public class Rental {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RentalId")
    private Integer rentalId;
    
    @Column(name = "ProductId")
    private Integer productId;
    
    @Column(name = "CustomerId")
    private Integer customerId;
    
    @NotNull
	@Column(name="ProductQuantity")
    private int quantity;
    
    @Column(name = "IssueDate")
    private LocalDate issueDate;
    
    @Column(name = "ReturnDate")
    private LocalDate returnDate;
    
    @Column(name = "RentalStatus")
    private String rentalStatus;
    
    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId",insertable=false, updatable=false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId",insertable=false, updatable=false)
    private Customer customer;

	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", productId=" + productId + ", customerId=" + customerId
				+ ", quantity=" + quantity + ", issueDate=" + issueDate + ", returnDate=" + returnDate
				+ ", rentalStatus=" + rentalStatus + ", product=" + product + ", customer=" + customer + "]";
	}

}
