package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	// ====== 응답(forward, redirect)과 관련된 내용 ======
	
	//@RequestMapping(value="/book/list.do", method=RequestMethod.GET)
	//@RequestMapping(value="book/list.do", method=RequestMethod.GET) //  / 생략가능
	//@RequestMapping(value="book/list.do")							  // method 생략시 둘다 허용
	@RequestMapping("book/list.do")									  // url mapping값만 작성시 value속성
	public String bookList() {
		
		// /WEB-INF/views/book/list.jsp 포워딩 
		return "book/list";
	}
	
	// 만일 포워딩할 뷰가 url 매핑값의 경로와 일치할 경우 void 메소드로 가능
	@RequestMapping("book/detail.do")
	public void bookDetail() {
		// /WEB-INF/views/book/detail.jsp 포워딩
		// return "book/detail";
	}
	
	@RequestMapping("book/modifyForm.do")
	public String bookModifyForm() {
		// /WEB-INF/views/book/modify.jsp 포워딩
		return "book/modify";
	}
	
	@RequestMapping(value="book/modify.do", method=RequestMethod.POST)
	public String bookModify() {
		// 성공했다는 가정하에  
		// "contextPath/book/detail.do?no=번호" url 재요청 (redirect)
		return "redirect:/book/detail.do?no=" + 1;
	}
	
	
	
	
	
	

}
