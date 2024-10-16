package com.br.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	// ======= 요청시 전달되는 값(parameter) 처리하는 방법 =======
	/*
	 * 1. HttpServletRequest 방법
	 * 2. @RequestParam 이용하는 방법
	 * 3. 커맨드 객체 이용하는 방법 
	 */
	
	/*
	 * 1. HttpServletRequest 방법
	 */
	@RequestMapping("/detail.do")
	public String memberDetail(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("조회할 회원번호: " + no);
		
		
		
		
		return "main";
	}
	
}
