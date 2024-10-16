package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	
	// ====== 응답(forward, redirect)과 관련된 내용 ======
	
	@RequestMapping(value="/book/list.do", method=RequestMethod.GET)
	public String bookList() {
		
	}

}
