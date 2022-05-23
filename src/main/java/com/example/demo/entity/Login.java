package com.example.demo.entity;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Login {
	
	private String name;
	private String code;
	private String password;
	private Boolean admin_flag;
	
	public Login() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getAdmin_flag() {
		return admin_flag;
	}
	
	public void setAdmin_flag(Boolean admin_flag) {
		this.admin_flag = admin_flag;
	}
}
