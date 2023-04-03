package com.antino.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	public Employee updateEmployeeDetails(Integer employeeId, Employee employeeUpdateRequest) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (employeeUpdateRequest.getEmployeeName() != null) {
                employee.setEmployeeName(employeeUpdateRequest.getEmployeeName());
            }
            if (employeeUpdateRequest.getEmployeeEmail() != null) {
                employee.setEmployeeEmail(employeeUpdateRequest.getEmployeeEmail());
            }
            employeeRepository.save(employee);
            return employee;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
	}

	public void deleteEmployee(Integer employeeId) {
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
		
	}

}
