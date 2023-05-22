package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.reply.service.IReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	@PostMapping("/register")
	public void register() {
		
	}
	
}
