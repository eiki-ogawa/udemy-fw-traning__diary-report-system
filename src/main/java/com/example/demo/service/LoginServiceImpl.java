package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao dao;
	
	public LoginServiceImpl(LoginDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Login> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Login> check(String code, String password) {
		try {
			return dao.check(code, password);
		} catch (EmptyResultDataAccessException e) {
			return Optional.ofNullable(null);
		}
	}
}