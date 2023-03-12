package com.antino.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class LoginDTO {

	@NotEmpty(message="Name cannot be null or empty.")
	@Size(min=1,message="*required") 
	@Column(name="EmployeeName")
	private String userName;
	
	@NonNull
	@NotBlank(message = "Password is mandatory")
	private String password;

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

}
