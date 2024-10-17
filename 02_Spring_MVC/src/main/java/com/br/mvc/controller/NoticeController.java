package com.br.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.mvc.dto.NoticeDto;
import com.br.mvc.service.NoticeService;

import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
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
	 * 		ㄴ @AllArgsContructor       : 모든 필드들에 대한 매개변수 생성자 
	 * 		ㄴ @RequiredArgsConstructor : final 필드에 있어서만 매개변수 생성자
	 */
	
	private String name;
	private int no;
	private final NoticeService noticeService; // 앞으로 사용할 DI 방식 (final + @RequiredArgsConstructor)
	
	// ====== 포워딩할 응답페이지에 필요한 데이터 담는 방법 ======
	
	/*
	 * 1. Model 객체 이용하기 
	 *    requestScope 영역에 데이터를 맵형식(key-value)로 담을 수 있는 객체 
	 *    단, setAttribute가 아닌 addAttribute 메소드 이용 
	 */
	
	@GetMapping("/list.do")
	public String noticeList(Model model) {
		List<NoticeDto> list = noticeService.selectNoticeList(); // 응답페이지에 필요한 데이터
		model.addAttribute("list", list);
		// /WEB-INF/views/notice/list.jsp 포워딩
		return "notice/list";
	}
	
	
	
	
	
	
	
	
	

}
