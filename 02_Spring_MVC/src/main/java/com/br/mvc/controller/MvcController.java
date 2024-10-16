package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
	
	/*
	 * * 스프링 사용 전 (서블릿클래스 제작)
	 * 
	 * @WebServlet("/ 또는 /main.do")
	 * 
	 * public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 *		// 1) 포워딩 할 경우
	 * 		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
	 * 
	 * 		// 2) redirect 경우
	 * 		response.sendRedirect("재요청할url");
	 * }
	 */
	
	@RequestMapping(value={"/", "/main.do"}, method=RequestMethod.GET)
	public String abcd() {
		return "main";
	}
	
	
	

}
