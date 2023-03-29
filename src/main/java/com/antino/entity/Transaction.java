package com.antino.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Transaction Details")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private Integer id;
	
	@Column(name="TransactionId")
	private String transactionId;
	
	@NotEmpty(message="Name cannot be null or empty.")
	@Size(min=1,message="*required") 
	@Column(name="CustomerName")
	private String customerName;
	
//	@Lob
//	@OneToMany(targetEntity=Product.class, mappedBy="productName",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "ProductName")
	@Column(name="ProductName")
	@NotEmpty(message="Name cannot be null or empty")
	@Size(min=1,message="*required") 
	private String productName;
	
	@NotNull
	@Column(name="Quantity")
	private Integer quantity;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Date")
	private Date createdAt;
	
	@Size(min=1,message="*required") 
	@Column(name="PurchaseType")
	private String purchaseType;
	
	@Column(name="TotalAmount")
	@DecimalMin("1.00")
	@NotNull
	private Double totalAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionId=" + transactionId + ", customerName=" + customerName
				+ ", productName=" + productName + ", quantity=" + quantity + ", createdAt=" + createdAt
				+ ", purchaseType=" + purchaseType + ", totalAmount=" + totalAmount + "]";
	}

}
