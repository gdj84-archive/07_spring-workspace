package com.br.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {
	
	@RequestMapping(value= {"/", "/main.do"})
	public String mainPage() {
		
		//System.out.println("MvcController의 mainPage 메소드 작동됨"); // 출력문은 성능저하를 야기시킴 => 로그 출력 권장
		
		return "main";
	}
}
