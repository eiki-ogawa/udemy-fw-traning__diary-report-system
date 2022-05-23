package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.service.NotFoundException;
import com.example.demo.service.UniqueException;

@ControllerAdvice
public class WebMvcControllerAdvice {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public String handleException(NotFoundException e, Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}
	@ExceptionHandler(UniqueException.class)
	public String handleException(UniqueException e, Model model) {
		model.addAttribute("message", e);
		return "/error/Unique";
	}

}
