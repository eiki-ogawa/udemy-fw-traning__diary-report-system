package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Reports;

public interface ReportsService {
	
	List<Reports> findAll();
	
	Optional<Reports> getReports(int id);
	
	void insert(Reports reports);
	
	void update(Reports reports);
	
	void deleteById(int id);

}
