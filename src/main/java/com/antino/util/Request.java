package com.antino.util;

import java.time.LocalDate;

public class Request {
	
	private Integer productId;
    private Integer userId;
    private int quantity;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private String rentalStatus;
    private Integer rentalId;
    public enum RentalStatus {
	    PENDING,
	    APPROVED,
	    REJECTED
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	
	public Integer getRentalId() {
		return rentalId;
	}
	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}
	@Override
	public String toString() {
		return "Request [productId=" + productId + ", userId=" + userId + ", quantity=" + quantity + ", issueDate="
				+ issueDate + ", returnDate=" + returnDate + ", rentalStatus=" + rentalStatus + ", rentalId=" + rentalId
				+ "]";
	}
    
}
