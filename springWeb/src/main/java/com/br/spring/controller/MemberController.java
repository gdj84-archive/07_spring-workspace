package com.br.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.spring.dto.MemberDto;
import com.br.spring.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/signin.do")
	public void signin(MemberDto m
					 , HttpServletResponse response
					 , HttpSession session
					 , HttpServletRequest request) throws IOException {
		
		MemberDto loginUser = memberService.selectMember(m);
		
		// 로그인 성공시 => 세션에 회원정보 담고, alert와 함께 메인 페이지가 보여지도록
		// 로그인 실패시 => alert와 함께 기존에 보던 페이지 유지
		
		// script문을 응답데이터로 돌려줘서 흐름제어 
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(loginUser != null) { // 로그인 성공
			session.setAttribute("loginUser", loginUser);
			out.println("alert('" + loginUser.getUserName() + "님 환영합니다~');");
			out.println("location.href = '" + request.getHeader("referer") + "';"); // 이전에 보던 페이지로 이동
		}else { // 로그인 실패
			out.println("alert('로그인에 실패하였습니다. 아이디 및 비밀번호를 다시 확인해주세요.');");
			out.println("history.back();");
		}
		out.println("</script>");
		
	}
	
	@GetMapping("/signout.do")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/signup.do")
	public void signupPage() {} // /WEB-INF/views/member/signup.jsp
	
	
	
	
}
