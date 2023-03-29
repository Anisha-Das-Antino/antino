package com.antino.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antino.entity.MyUser;
import com.antino.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			System.out.println("UserName...."+username);
			MyUser user=userRepository.findByUserName(username);
			System.out.println("User...."+user.toString());
	
			if(username.equals(user.getUserName())) {
			return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
			}else {
				throw new UsernameNotFoundException("User not found");
			}
		}
		catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
		
	}

	public MyUser addUser(@Valid User user) {
		System.out.println("Inside Employee Service Package"+user.toString());
		addUser(user).setCreatedAt(new Date());
		
		return userRepository.save(user);
	}

	public List<MyUser> getAllUser() {
		
		return userRepository.findAll();
	}

	public Page<MyUser> getUserPagination(Integer pageNumber, Integer pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return userRepository.findAll(pageable);
	}

}
