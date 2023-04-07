package com.antino.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    @Column(name = "UserId")
    private Integer userId;
    
    @NotNull
	@Column(name="ProductQuantity")
    private int quantity;
    
    @Column(name = "IssueDate")
    private LocalDate issueDate;
    
    @Column(name = "ReturnDate")
    private LocalDate returnDate;
    
    @Column(name = "RentalStatus")
    private String rentalStatus;
    
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ProductId",insertable=false, updatable=false)
    private Product product;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "Id",insertable=false, updatable=false)
    private MyUser myUser;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", productId=" + productId + ", userId=" + userId + ", quantity="
				+ quantity + ", issueDate=" + issueDate + ", returnDate=" + returnDate + ", rentalStatus="
				+ rentalStatus + ", product=" + product + ", myUser=" + myUser + "]";
	}
	
}
