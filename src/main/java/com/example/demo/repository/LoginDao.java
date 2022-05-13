package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Login;

public interface LoginDao {
	
	List<Login> findAll();
	
	Optional<Login> check(String code, String password);
	
}
