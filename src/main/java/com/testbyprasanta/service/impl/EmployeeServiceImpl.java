package com.testbyprasanta.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testbyprasanta.exception.ResourceNotFoundException;
import com.testbyprasanta.model.Employee;
import com.testbyprasanta.repository.EmployeeRepository;
import com.testbyprasanta.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		Optional<Employee> savedEmployee=employeeRepository.findByEmail(employee.getEmail());
		
		if(savedEmployee.isPresent()) {
			throw new ResourceNotFoundException("Employee already exist with given email :"+employee.getEmail());
		}
		return employeeRepository.save(employee);
	}
	
}
