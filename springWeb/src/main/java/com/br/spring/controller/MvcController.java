package com.br.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
	@GetMapping("/")
	public String mainPage() {
		return "main";
	}
}
