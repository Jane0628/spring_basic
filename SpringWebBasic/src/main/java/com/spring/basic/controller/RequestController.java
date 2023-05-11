package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 자동으로 스프링 컨테이너에 해당 클래스의 빈을 등록하는 아노테이션
// 빈을 등록해 놔야 HandlerMapping이 이 클래스의 객체를 검색할 수 있을 것이다.
// @Controller("merong")  이름을 부여하지 않으면 클래스의 이름으로 지정되고 이름을 지정해주면 해당 이름으로 지정된다.

@Controller
public class RequestController {

	public RequestController() {
		System.out.println("RequestCon 생성!");
	}
	
	@RequestMapping("/request/test")
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴!");
		return "test";
	}
	
	/*
	    만약 사용자가 /request/req 요청을 보내왔을 때
	    views 폴더 아래에 request 폴더 안에 존재하는
	    req-ex01.jsp파일을 열도록 메서드를 구성해 보세요.
   */
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.GET)
	@GetMapping("/request/basic01")
	public String req() {
		System.out.println("/request/basic01 GET 방식 요청이 들어옴!");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "/request/basic01", method = RequestMethod.POST)
	@PostMapping("/request/basic01")
	public String basic() {
		System.out.println("/request/basic01 POST 방식 요청이 들어옴!");
		return "request/req-ex01";
	}
	
}
