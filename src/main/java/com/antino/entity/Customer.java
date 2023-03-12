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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Customer Details")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@NotNull(message="Id cannot be null.") 
	@Column(name="CustomerId")
	private Integer customerId;
	
	@NotEmpty(message="Name cannot be null or empty.")
	@Size(min=1,message="*required") 
	@Column(name="CustomerName")
	private String customerName;
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@Column(name="CustomerEmail")
	private String customerEmail;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})",message = "PhoneNo should be exact 10 characters.")
	@Column(name="CustomerPhone")
	private String customerPhone;
	
	@NotEmpty(message = "Please provide address.")
	@Size(min = 10, max = 100, message = "Address must be between 10 and 200 characters")
	@Column(name="CustomerAddress")
	private String customerAddress;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreatedAt")
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerId=" + customerId + ", customerName=" + customerName
				+ ", customerEmail=" + customerEmail + ", customerPhone=" + customerPhone + ", customerAddress="
				+ customerAddress + ", createdAt=" + createdAt + "]";
	}
}
