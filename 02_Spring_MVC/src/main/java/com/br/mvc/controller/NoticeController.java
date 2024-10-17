package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.mvc.service.NoticeService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@RequestMapping("/notice")
@Controller
public class NoticeController {
	
	//private NoticeService noticeService = new NoticeServiceImpl(); // 직접 생성하면 결합도가 높아지는 문제 발생
	
	// 1) 필드 주입
	/*
	@Autowired
	private NoticeService noticeService;
	*/
	
	// 2) 메소드 주입 (@Autowired 생략 불가)
	/*
	private NoticeService noticeService;
	
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	*/
	
	// 3) 생성자 주입 (@Autowired 생략 가능) => 주로 사용
	/*
	private NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	*/
	
	/*
	 * * 생성자 주입(DI)을 주로 하는 이유
	 * 1) 생성자 같은 경우 @Autowired 을 생략할 수 있음 
	 * 2) 롬복 라이브러리를 사용할 경우 매개변수 생성자를 만들어주는 어노테이션으로 쉽게 대체 가능
	 * 		ㄴ @AllArgsContructor  : 모든 필드들에 대한 매개변수 생성자 
	 */
	
	private String name;
	private int no;
	private final NoticeService noticeService;
	
	
	@GetMapping("/list.do")
	public String noticeList() {
		
	}

}
