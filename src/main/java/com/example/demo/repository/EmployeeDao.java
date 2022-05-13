package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;

public interface EmployeeDao {
	
	List<Employee> findAll();
	
	Optional<Employee> findById(int id);
	
	void insert(Employee employee);
	
	int update(Employee employee);
	
	int deleteById(int id);
}
