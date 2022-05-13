package com.example.demo.entity;

import java.time.LocalDateTime;

public class Employee {
	
	private int id;
	private String code;
	private String name;
	private String password;
	private boolean admin_flag;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private boolean delete_flag;
	
	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAdmin_flag() {
		return admin_flag;
	}

	public void setAdmin_flag(boolean admin_flag) {
		this.admin_flag = admin_flag;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public boolean getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}
}
