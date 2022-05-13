package com.example.demo.app.login;

import javax.validation.constraints.NotNull;

public class LoginForm {
	
	@NotNull (message = "社員番号を入力してください")
	private String code;
	
	@NotNull (message = "パスワードを入力してください")
	private String password;
	
	public LoginForm(String code,String password) {
		this.code = code;
		this.password = password;
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
}
