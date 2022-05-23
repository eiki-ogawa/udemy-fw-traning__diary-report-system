package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Login;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public LoginDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Login> findAll() {
		String sql = "SELECT name, code, password, admin_flag FROM employee";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		
		List<Login> list = new ArrayList<Login>();
		
		for(Map<String, Object> result : resultList) {
			
			Login login = new Login();
			login.setName((String)result.get("name"));
			login.setCode((String)result.get("code"));
			login.setPassword((String)result.get("password"));
			login.setAdmin_flag((Boolean)result.get("admin_flag"));
			
			list.add(login);
		}
		return list;
	}

	@Override
	public Optional<Login> check(String code, String password) {
		String sql = "SELECT name, code, password, admin_flag FROM employee WHERE code = ? AND password = ?";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, code, password);
		
		Login login = new Login();
		login.setName((String)result.get("name"));
		login.setCode((String)result.get("code"));
		login.setPassword((String)result.get("password"));
		login.setAdmin_flag((Boolean)result.get("admin_flag"));
		
		Optional<Login> loginOpt = Optional.ofNullable(login);
		
		return loginOpt;
		}

}
