package com.example.demo.app.login;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Login;
import com.example.demo.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private LoginController loginController;
		
		private final LoginService loginService;
		
		public LoginController(LoginService loginService) {
			this.loginService = loginService;
			
		}
		
		@GetMapping
		public String Login(
				LoginForm loginForm,
				@PathVariable String code,
				@PathVariable String password,
				Model model) {
			
			Optional<Login> LoginOpt = loginService.check(code, password);
			
			if(LoginOpt.isPresent()) {
				return "employee/index";
			}
			
			return "/login";
		}
	}
