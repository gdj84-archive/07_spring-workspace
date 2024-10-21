package com.br.ajax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.ajax.dto.MemberDto;
import com.br.ajax.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member1")
@RequiredArgsConstructor
@Controller
public class MemberController1 {
	
	private final MemberService memService;
	private Logger logger = LoggerFactory.getLogger(MemberController1.class);
	
	// 기존에 HttpServletResponse 객체 이용하는 방식
	/*
	@GetMapping("/detail1.do")
	public void memberDetail(String id, String pwd, HttpServletResponse response) throws IOException {
		logger.debug("request id: {}, pwd: {}", id, pwd);
		String result = memService.selectMemberByIdPwd(id, pwd);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}
	*/
	
	/*
	 * * @ResponseBody
	 * - 비동기식으로 데이터 응답시 필요한 어노테이션
	 * - 해당 어노테이션이 붙은 메소드에서의 반환 값은
	 *   응답뷰가 아닌 어떤 data(text, json, xml 등)라는 걸 의미
	 */
	@ResponseBody
	@GetMapping(value="/detail1.do", produces="text/html; charset=utf-8")
	public String memberDetail1(String id, String pwd)  {
		logger.debug("request id: {}, pwd: {}", id, pwd);
		String result = memService.selectMemberByIdPwd(id, pwd); // 홍길동
		
		return result;
	}
	
	@ResponseBody
	@GetMapping(value="/detail2.do", produces="text/html; charset=utf-8")
	public String memberDetail2(String userId, String userPwd) {
		//String result = memService.selectMemberByIdPwd(userId, userPwd);
		return memService.selectMemberByIdPwd(userId, userPwd);
	}
	
	/*
	 * 자바 객체 (Dto, List, Map, 배열 등)를 응답할때는 json 데이터로 변환해서 응답해야됨
	 * 자동으로 변환 처리해주는 jackson 라이브러리 등록하면 바로 반환만 하면됨
	 */
	@ResponseBody
	@GetMapping(value="/detail3.do", produces="application/json")
	public MemberDto memberDetail3(@RequestParam(value="no", defaultValue="1") int userNo) {
		MemberDto mem = memService.selectMemberByNo(userNo);
		return mem; // {필드명:필드값, 필드명:필드값, ..}
	}
	
	@ResponseBody
	@GetMapping(value="list.do", produces="application/json")
	public List<MemberDto> memberList() {
		List<MemberDto> list = memService.selectMemberList();
		return list; // [{}, {}, {}]
	}
	
	@ResponseBody
	@GetMapping(value="etc1.do", produces="application/json")
	public Map<String, Object> responseMapTest() {
		// 만일 응답할 데이터로 숫자, List, Dto가 있다는 가정하에
		Map<String, Object> map = new HashMap<>();
		map.put("no", 1);
		map.put("list", memService.selectMemberList());
		map.put("m", memService.selectMemberByNo(2));
		
		return map;
		/*
		 * {
		 * 	 no: 1,
		 *   list: [{}, {}, {}],
		 *   m: {}
		 * }
		 */
	}
	
	@ResponseBody
	@PostMapping(value="/etc2.do")
	public void requestBodyTest(@RequestBody Map<String, Object> map) {
		logger.debug("map: {}", map);
		logger.debug("map>no: {}", map.get("no"));
		logger.debug("map>name: {}", map.get("name"));
		logger.debug("map>arr: {}", map.get("arr"));
	}
	
	
	
	
	
	
	
	
	

}
