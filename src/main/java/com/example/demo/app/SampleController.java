package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * test画面の表示
 */
@Controller
@RequestMapping("/")
public class SampleController {
	
	@GetMapping
	public String test() {
		return "test";
	}

}