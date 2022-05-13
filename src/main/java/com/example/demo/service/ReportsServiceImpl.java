package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Reports;
import com.example.demo.repository.ReportsDao;

@Service
public class ReportsServiceImpl implements ReportsService {

	private final ReportsDao dao;
	
	public ReportsServiceImpl(ReportsDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Reports> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Reports> getReports(int id) {
		return dao.findById(id);
	}

	@Override
	public void insert(Reports reports) {
		dao.insert(reports);

	}

	@Override
	public void update(Reports reports) {
		dao.update(reports);

	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
