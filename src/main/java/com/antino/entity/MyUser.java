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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
public class MyUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="Id")
	private int id;
	
	@NotEmpty(message="Name cannot be null or empty.")
	@Size(min=1,message="*required") 
	@Column(name="USER_NAME", unique=true)
	private String userName;
	
	@Column(name="PASSWORD", length=60)
	private String password;
	
	@Column(name="ROLE", length=20)
	private String role;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
    @NotBlank(message="{register.email.invalid}")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message="{register.email.invalid}")
	@Column(name="USER_EMAIL", unique=true)
	private String userEmail;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreatedAt")
	private Date createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "MyUser [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", userEmail=" + userEmail + ", createdAt="
				+ createdAt + "]";
	}

}
