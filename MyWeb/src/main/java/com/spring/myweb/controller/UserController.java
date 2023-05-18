package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.user.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Controller @Slf4j
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	// 회원가입 페이지로 이동
	@GetMapping("/userJoin")
	public void userJoin() {}
	
	// 로그인 페이지로 이동
	@GetMapping("/userLogin")
	public void userLogin() {}
	
	// 마이 페이지로 이동
	@GetMapping("/userMypage")
	public void userMypage() {}
	
}
