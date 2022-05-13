package com.example.demo.app.employee;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeForm {
	
	@NotNull (message = "社員番号を入力してください。")
	private String code;
	
	@NotNull (message = "氏名を入力してください。")
	private String name;
	
	@NotNull (message = "パスワードを入力してください。")
	private String password;

	private Boolean admin_flag;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime updated_at;
		
	public EmployeeForm(String code,
			String name,
			String password,
			Boolean admin_flag,
			LocalDateTime updated_at) {
		this.code = code;
		this.name = name;
		this.password = password;
		this.admin_flag = admin_flag;
		this.updated_at = updated_at;
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

	public Boolean getAdmin_flag() {
		return admin_flag;
	}

	public void setAdmin_flag(Boolean admin_flag) {
		this.admin_flag = admin_flag;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
}

