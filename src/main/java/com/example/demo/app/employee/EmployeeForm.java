package com.example.demo.app.employee;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeForm {
	
	@NotNull (message = "社員番号を入力してください")
	private String code;
	
	@NotNull (message = "名前を入力してください")
	private String name;
	
	@NotNull (message = "パスワードを入力してください")
	private String password;

	@NotNull (message = "管理者権限を選択してください")
	private Boolean admin_flag;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime created_at;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime updated_at;
	
	private Boolean delete_flag;
	
	private Boolean New;
		
	public EmployeeForm(String code,
			String name,
			String password,
			Boolean admin_flag,
			LocalDateTime created_at,
			LocalDateTime updated_at,
			Boolean delete_flag,
			Boolean New) {
		this.code = code;
		this.name = name;
		this.password = password;
		this.admin_flag = admin_flag;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.delete_flag = delete_flag;
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
	
	public Boolean getDelete_flag() {
		return delete_flag;
	}
	
	public void setDelete_flag(Boolean delete_flag) {
		this.delete_flag = delete_flag;
	}

	public Boolean getNew() {
		return New;
	}

	public void setNew(Boolean new1) {
		New = new1;
	}
}

