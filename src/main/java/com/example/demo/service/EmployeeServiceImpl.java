package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDao dao;
	
	public EmployeeServiceImpl(EmployeeDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}
	
	@Override
	public String getName(String employee_id) {
		return dao.findByCode(employee_id);
	}

	@Override
	public Optional<Employee> getEmployee(int id) {
		try {
			return dao.findById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("指定されたタスクが存在しません");
		}
	}

	@Override
	public void insert(Employee employee) {
		try {
			dao.insert(employee);
		} catch (Exception e) {
			throw new UniqueException("同じ社員番号が既に登録されています。");
		}
	}

	@Override
	public void update(Employee employee) {
		dao.update(employee);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
