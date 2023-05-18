package com.spring.myweb.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@RestController
@RequestMapping("/rest")
public class RestControllerTest {

	/*
	 	@ResponseBody
	 	- 메서드가 리턴하는 데이터를 viewResolver에게 전달하지 않고 클라이언트에게 해당 데이터를 바로 응답하게 합니다.
	 	  비동기 통신에서 주로 많이 사용합니다.
	 	- 컨트롤러 빈 등록 시 @RestController를 사용하면 모든 메서드에 @ResponseBody를 붙인 것과 같다.
	 	- @RestController 내에서 일반적인 jsp 파일을 이용하여 응답하고 싶다면 return type을 ModelAndView 객체로 처리하면 됩니다.
	 */
	
	@GetMapping("/hello")
//	@ResponseBody
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/hobby")
//	@ResponseBody
	public List<String> hobby() {
		List<String> hobby = Arrays.asList("축구", "영화 감상", "수영");
		return hobby;
	}
	
	@GetMapping("/study")
	public Map<String, Object> study() {
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("스프링", "spring framework");
		
		return subject;
	}
	
	@GetMapping("/mv")
	public ModelAndView mv() {
		ModelAndView model = new ModelAndView();
		model.setViewName("temp/test");
		
		return model;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	@GetMapping("/getObject")
	public Person getObject() {
		
	}
	
}
