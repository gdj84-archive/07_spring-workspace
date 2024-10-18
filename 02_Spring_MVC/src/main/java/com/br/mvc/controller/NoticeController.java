package com.br.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/*
	 * 2. ModelAndView 객체 이용하기
	 *    Model과 View가 합쳐져있는 형태
	 *    Model은 데이터를 담는 객체
	 *    View는 응답뷰에 대한 정보를 담는 객체
	 *    
	 *    ModelAndView 객체에 데이터와 응답뷰에 대해 담고 해당 객체를 반환 
	 */
	@GetMapping("/detail.do")
	public ModelAndView noticeDetail(int no, ModelAndView mv) {
		//NoticeDto n = noticeService.selectNoticeByNo(no); // 응답페이지에 필요한 데이터 
		//return "notice/detail"; // 응답뷰
		
		//mv.addObject("notice", noticeService.selectNoticeByNo(no));
		//mv.setViewName("notice/detail");
		
		mv.addObject("notice", noticeService.selectNoticeByNo(no))
		  .setViewName("notice/detail");
		
		return mv;
	}
	
	
	/*
	 * 										   @Controller	 @Service @Repository
	 * view ---request---> DispatcherServlet => Controller => Service => Dao (쿼리실행)
	 *      <--response--    ViewResolver    <= 데이터,뷰  <=  결과   <= 결과
	 */
	
	@RequestMapping("/modifyForm.do")
	public String noticeModifyForm(int no, Model model) {
		NoticeDto notice = noticeService.selectNoticeByNo(no);
		model.addAttribute("notice", notice);
		return "notice/modifyForm";
	}
	
	// ========= redirect시 그때 포워딩 되는 페이지에 필요한 데이터 담는 방법 ======
	/*
	 * Model 영역은 requestScope 기 때문에 당장 포워딩되는 jsp에서만 사용 가능함 
	 * 즉, redirect로 다른 controller 가 실행되는 순간 현재 만들어진 Model은 소멸됨
	 * 
	 *   case 1)   
	 *   			Model1 생성              Model1 유지
	 *   			controller ------------> 포워딩 jsp
	 *   						  포워딩
	 *   
	 *   
	 *   
	 *   case 2) 	
	 *   			Model1 생성 Model1소멸   Model2 생성             Model2 유지
	 *   			controller ------------> controller -----------> 포워딩 jsp
	 *   				|		 redirect 		 |		   포워딩		 |
	 *   				|						 |						 |
	 *   			/update.do				 /detail.do 			 /notice/detail.jsp
	 *   
	 *   case 3)
	 *   		RedirectAttributes 생성 						RedirectAttributes 유지
	 *   			controller ------------> controller ----------->  포워딩 jsp
	 *    						 redirect				   포워딩
	 * 
	 * 		즉, 현재 controller에서 url 재요청을 통해 다른 controller에 의해 포워딩되는 jsp에서까지
	 * 		데이터를 쭉 사용하고 싶을 경우 
	 * 		Model 객체가 아닌 RedirectAttributes 객체에 "addFlashAttribute" 메소드로 담으면 됨 
	 * 		addAttribute 담게되면 Model에 담는거랑 동일한 맥락
	 */
	
	
	@PostMapping("/update.do")
	public String noticeUpdate(NoticeDto n, /*Model model*/ RedirectAttributes ra) { // 1) 요청시 전달값 처리
		
		// 2) 요청처리를 위한 서비스 호출
		int result = noticeService.updateNotice(n);
		
		// 3) 응답
		if(result > 0) {
			//model.addAttribute("alertMsg", "성공적으로 수정되었습니다.");
			ra.addFlashAttribute("alertMsg", "성공적으로 수정되었습니다.");
			return "redirect:/notice/detail.do?no=" + n.getNo();
		}else {
			return "redirect:/";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
