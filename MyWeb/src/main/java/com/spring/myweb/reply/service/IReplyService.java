package com.spring.myweb.reply.service;

import java.util.List;

import com.spring.myweb.command.ReplyVO;

public interface IReplyService {

	// 댓글 등록
	void replyRegister(ReplyVO vo);

	// 목록 요청
	List<ReplyVO> getList(int bno, int pageNum);

	// 댓글 개수 (페이징, PageCreator는 사용하지 않습니다. (버튼을 사용하지 않기 때문))
	int getTotal(int bno);

	// 비밀번호 확인
	boolean pwCheck(ReplyVO vo);

	// 댓글 수정
	void update(ReplyVO vo);

	// 댓글 삭제
	void delete(int rno);

}
