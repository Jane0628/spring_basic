package com.spring.myweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {

	@Autowired
	private IFreeBoardService service;
	
	// 목록 화면
	@GetMapping("/freeList")
	public void freeList(Model model) {
		model.addAttribute("boardList", service.getList());
	}
	
	// 글쓰기 페이지 열어주는 메서드
	@GetMapping("/freeRegister")
	public void register() {}
	
	// 글 등록 처리
	@PostMapping("/freeRegister")
	public String register(FreeBoardVO vo) {
		service.register(vo);
		return "redirect:/freeBoard/freeList";
	}
	
	// 글 상세 보기 화면
	@GetMapping("/content")
	public String content(int bno, Model model) {
		model.addAttribute("content", service.getContent(bno));
		return "freeBoard/freeDetail";
	}
	
	// 글 수정 화면
	@GetMapping("/freeModify")
	public void modify(@ModelAttribute("article") FreeBoardVO vo) {}
	
	// 글 수정 처리
	@PostMapping("/freeModify")
	public String update(FreeBoardVO vo) {
		service.update(vo);
		return "redirect:/freeBoard/content?bno=" + vo.getBno();
	}
	
	// 글 삭제 처리
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeBoard/freeList";
	}
	
}
