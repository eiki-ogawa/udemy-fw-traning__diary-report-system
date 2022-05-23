package com.example.demo.app.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Login;
import com.example.demo.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	Login login;
	
	private LoginController loginController;
		
	private final LoginService loginService;
		
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
		
	}
	
	@GetMapping
	public String Login(
			LoginForm loginForm,
			Model model) {
		return "login/index";
	}
	
	@PostMapping("/check")
	public String LoginCheck(
			LoginForm loginForm,
			String code,
			String password,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		Optional<Login> loginOpt = loginService.check(code, password);
		
		if(loginOpt.isPresent()) {
			
			Optional<LoginForm> loginFormOpt = loginOpt.map(l -> makeLoginForm(l));
			loginForm = loginFormOpt.get();
			
			login.setName(loginForm.getName());
			login.setCode(loginForm.getCode());
			login.setPassword(loginForm.getPassword());
			login.setAdmin_flag(loginForm.getAdmin_flag());
			
			model.addAttribute(login);
			redirectAttributes.addFlashAttribute("complete", "ログインしました");
			return "redirect:/reports";
			
		} else {
			redirectAttributes.addFlashAttribute("warning", "社員番号またはパスワードが間違っています");
		return "redirect:/login";
		}
	}
	private LoginForm makeLoginForm(Login login) {
		LoginForm loginForm = new LoginForm(null, null, null, false);
		
		loginForm.setName(login.getName());
		loginForm.setCode(login.getCode());
		loginForm.setPassword(login.getPassword());
		loginForm.setAdmin_flag(login.getAdmin_flag());
		
		return loginForm;
	}
}
