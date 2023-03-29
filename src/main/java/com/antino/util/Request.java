package com.antino.util;

import java.time.LocalDate;

public class Request {
	
	private Integer productId;
    private Integer customerId;
    private int quantity;
    private LocalDate issueDate;
    private LocalDate returnDate;
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
	@Override
	public String toString() {
		return "Request [productId=" + productId + ", customerId=" + customerId + ", quantity=" + quantity
				+ ", issueDate=" + issueDate + ", returnDate=" + returnDate + "]";
	}

}
