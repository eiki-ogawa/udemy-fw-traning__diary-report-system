package com.example.demo.app.login;

import javax.validation.constraints.NotNull;

public class LoginForm {
	
	private String name;
	
	@NotNull (message = "�Ј��ԍ�����͂��Ă�������")
	private String code;
	
	@NotNull (message = "�p�X���[�h����͂��Ă�������")
	private String password;
	
	private Boolean admin_flag;
	
	public LoginForm(String name,
			String code,String password,
			Boolean admin_flag) {
		this.name = name;
		this.code = code;
		this.password = password;
		this.admin_flag = admin_flag;
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
