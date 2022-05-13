package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Reports;

public interface ReportsDao {
	
	List<Reports> findAll();
	
	Optional<Reports> findById(int id);
	
	void insert(Reports reports);
	
	int update(Reports reports);
	
	int deleteById(int id);

}
