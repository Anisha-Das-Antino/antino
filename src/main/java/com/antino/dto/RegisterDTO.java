package com.antino.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class RegisterDTO {
	
	@NotEmpty(message="Name cannot be null or empty.")
	@Size(min=1,message="*required") 
	@Column(name="EmployeeName")
	private String userName;
	
	@NotEmpty(message="Email cannot be null or empty.")
	@NotBlank(message="{register.email.invalid}")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message="{register.email.invalid}")
	@Column(name="EmployeeEmail")
	private String userEmail;
	
	@NonNull
	@NotBlank(message = "New password is mandatory")
	private String password;
	
	@NonNull
	@NotBlank(message = "role is mandatory")
	private String role;
	
	@NonNull
	@NotBlank(message = "address is mandatory")
	private String address;
	
	@NonNull
	@NotBlank(message = "phoneNumber is mandatory")
	private String phoneNumber;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	@Override
	public String toString() {
		return "RegisterDTO [userName=" + userName + ", userEmail=" + userEmail + ", password=" + password + ", role="
				+ role + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

}
