package com.antino.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.antino.entity.Employee;

import com.antino.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee addEmployee(@Valid Employee employee) {
		System.out.println("Inside Employee Service Package"+employee.toString());
		employee.setCreatedAt(new Date());
		
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, ("createdAt"));
		return employeeRepository.findAll(pageable);
		
	}

}
