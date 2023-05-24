package com.spring.myweb.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/fileupload")
public class UploadController {
	
	@GetMapping("/upload")
	public void form() {}
	
	@PostMapping("/upload_ok")
	public void upload(MultipartFile file) {
		String fileRealName = file.getOriginalFilename(); 	// 파일 원본명
		long size = file.getSize(); 						// 파일 크기
		
		log.info("파일명 : " + fileRealName);
		log.info("파일 크기 : " + size + "bytes");
		
		/*
		 	사용자가 첨부한 파일을 DB에 저장하는 것은 선호되지 않습니다.
		 	DB 용량을 파일 첨부에 사용하는 것은 비용적으로도 좋지 않기 때문입니다.
		 	그렇기 때문에 상용되는 서비스들이 파일을 처리하는 방법은 따로 파일 전용 서버를 구축하여 저장하고, DB에는 파일의 저장 경로를 지정하는 것이 일반적입니다.
		 	
		 	파일 업로드 시 파일명이 동일한 파일이 이미 존재할 수도 있고, 사용자가 업로드하는 파일명이 영어 이외의 언어로 되어있을 수 있습니다.
		 	타 언어를 지원하지 않는 환경에서는 정상 동작이 되지 않을 수도 있습니다. (리눅스)
		 	고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일명을 새롭게 만들어줍니다.
		 */
		UUID uuid = UUID.randomUUID();
		log.info("uuid : " + uuid.toString());
	}
}