package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Login;

public interface LoginService {
	
	List<Login> findAll();
	
	Optional<Login> check(String code, String password);
}
