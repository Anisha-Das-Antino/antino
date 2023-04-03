package com.antino.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="Product-Book Details")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private Integer id;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@NotNull(message="Id cannot be null.") 
	@Column(name="ProductId")
	private Integer productId;
	
//	@ManyToOne()
//	@JoinColumn(name="productName", referencedColumnName = "productName", insertable = false, updatable = false)
	@Column(name="ProductName", unique=true)
	@Size(min=1,message="*required")
	private String productName;
	
	@Size(min=1,message="*required") 
	@Column(name="Vendor")
	private String vendor;
	
	@NotEmpty(message = "Please provide description.")
	@Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
	@Column(name="ProductDescription")
	private String productDescription;
	
	@NotNull
	@Column(name="ProductQuantity")
	private Integer quantity;
	
	@NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
	@Column(name="ProductPrice")
    private BigDecimal price;
	
	@NotNull
    @DecimalMin("1.00")
	@Column(name="ProductGstTax")
    private BigDecimal gst;
	
	@NotEmpty
	@Column(name="Category")
	private String category;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreatedAt")
	private Date createdAt;
	
	@Size(min=1,message="*required") 
	@Column(name="Purchase Type")
	private String purchaseType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getGst() {
		return gst;
	}

	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", productName=" + productName + ", vendor=" + vendor
				+ ", productDescription=" + productDescription + ", quantity=" + quantity + ", price=" + price
				+ ", gst=" + gst + ", category=" + category + ", createdAt=" + createdAt + ", purchaseType="
				+ purchaseType + "]";
	}

}
