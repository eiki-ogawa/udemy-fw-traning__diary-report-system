package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	Optional<Employee> getEmployee(int id);
	
	void insert(Employee employee);
	
	void update(Employee employee);
	
	void deleteById(int id);
}
