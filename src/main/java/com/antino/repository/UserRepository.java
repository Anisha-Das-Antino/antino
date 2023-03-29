package com.antino.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.antino.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer>{
	
	@Query("select myuser from MyUser myuser where myuser.userName = :userName")
	public MyUser findByUserName(@Param("userName") String userName);

	public MyUser save(@Valid User user);

	@Query("select myuser from MyUser myuser where myuser.userEmail = :userEmail")
	public MyUser findByUserEmail(@Param("userEmail") String userEmail);
	
	@Query("select myuser from MyUser myuser where myuser.role = :role")
	public MyUser findByRole(@Param("role") String role);

}
