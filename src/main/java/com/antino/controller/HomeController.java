package com.antino.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.antino.dto.LoginDTO;
import com.antino.dto.RegisterDTO;

import com.antino.entity.MyUser;
import com.antino.repository.UserRepository;

import com.antino.service.UserService;

import com.antino.util.JwtUtil;
import com.antino.util.Response;

@RestController
@CrossOrigin
public class HomeController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/login")
	public Response login(@RequestBody LoginDTO login) throws Exception {
		System.out.println("Inside Login API"+login.toString());
		
		try {
			
			
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
			}
			catch(UsernameNotFoundException exp){
				exp.printStackTrace();
				throw new Exception("Bad Credential");
			}
			final UserDetails userDetails = userService.loadUserByUsername(login.getUserName());
			
			MyUser user=userRepository.findByUserName(login.getUserName());

			final String token = jwtUtil.generateToken(userDetails);
			
			Map<String,String> hpToken = new HashMap<String,String>();
			hpToken.put("token", token);
			hpToken.put("userName", user.getUserName());
			hpToken.put("role", user.getRole());
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("User login successfully");
			response.setResponse(hpToken); 
			return response;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
		}
		

		
	}
	@PostMapping("/register")
	public Response register(@RequestBody RegisterDTO register) {
		System.out.println("Register Data to be Saved"+register.toString());
		
		try {
			
//			MyUser myUserEmail = userRepository.findByUserEmail(register.getUserEmail());
//			MyUser myUserName = userRepository.findByUserName(register.getUserName());
//			if(myUserEmail!=null || myUserName != null) {
//				throw new Exception("User already exist");
//			}
			MyUser myUserRole = userRepository.findByRole(register.getRole());
			if(myUserRole!=null) {
				throw new Exception("User Owner already exist");
			}
			MyUser myUser = new MyUser();
			myUser.setUserName(register.getUserName());
			myUser.setPassword(passwordEncoder.encode(register.getPassword()));
			myUser.setPhoneNumber(register.getPhoneNumber());
			myUser.setRole(register.getRole());
			myUser.setUserEmail(register.getUserEmail());
			myUser.setAddress(register.getAddress());
			myUser.setCreatedAt(new Date());
			userRepository.save(myUser);	
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("User register successfully");
			response.setResponse(register);
			return response;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage(ex.getMessage());
			response.setResponse(null); 
			return response;
		}
	}
	
	@PostMapping("/addUser")
	public Response addUser(@Valid @RequestBody User user) {
		System.out.println("Employee is saved successfully"+user.toString());
		try {
			MyUser savedUser = this.userService.addUser(user);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("Employee Details saved successfully");
			response.setResponse(savedUser);
			return response;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
		}
	
	}
	
	@GetMapping("/totalUser")
	public Response getAllUser(){
		
		try {
			
			List<MyUser> userList = userService.getAllUser();
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("User Details fetched successfully!");
			response.setResponse(userList); 
			return response;
			
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
			
		}
		
	}
	
	@RequestMapping(value = "/pagingAndSortingUser/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public Response userPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize){
		
		try {
			
			Page<MyUser> userList = userService.getUserPagination(pageNumber, pageSize);
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("User Details fetched successfully!");
			response.setResponse(userList); 
			return response;
			
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage("Internal Server Error");
			response.setResponse(null); 
			return response;
			
		}
		
	}
	
	@PostMapping("/register/employee")
	public Response registerEmployee(@RequestBody RegisterDTO register) {
		System.out.println("Register Data to be Saved"+register.toString());
		
		try {
			
			MyUser myUserEmail = userRepository.findByUserEmail(register.getUserEmail());
			MyUser myUserName = userRepository.findByUserName(register.getUserName());
			if(myUserEmail!=null || myUserName != null) {
				throw new Exception("User already exist");
			}
			
			MyUser myUser = new MyUser();
			myUser.setUserName(register.getUserName());
			myUser.setPassword(passwordEncoder.encode(register.getPassword()));
			myUser.setPhoneNumber(register.getPhoneNumber());
			myUser.setRole(register.getRole());
			myUser.setUserEmail(register.getUserEmail());
			myUser.setAddress(register.getAddress());
			myUser.setCreatedAt(new Date());
			userRepository.save(myUser);	
			
			Response response = new Response();
			response.setStatusCode(200);
			response.setMessage("User register successfully");
			return response;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			Response response = new Response();
			response.setStatusCode(500);
			response.setMessage(ex.getMessage());
			response.setResponse(null); 
			return response;
		}
	}

}
