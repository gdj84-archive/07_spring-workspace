package com.br.spring.di;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhoneController1 {
	
	/*
	 * * Container에 등록된 Bean을 가져오는 방법
	 * 
	 * > 가장 근본적인 방법 : 빈들이 담겨있는 Spring 컨테이너 객체로 부터 getBean으로 가져오기
	 * > DI 관련 어노테이션을 이용하는 방법
	 *   - @Inject    : 등록된 Bean 들 중 타입(class)이 일치하는 Bean 객체를 주입
	 *   - @Resource  : 등록된 Bean 들 중 이름(id)이 일치하는 Bean 객체를 주입
	 *   - @Autowired : 등록된 Bean 들 중 타입(class)이 일치하는 Bean 객체를 주입 (주로 사용)
	 */
	
	@RequestMapping("/test1") // localhost:8888/spring/test1  url요청시 실행되는 메소드
	public void diTest1() {
		
	}

}
