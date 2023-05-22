package com.spring.myweb.command;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 	CREATE TABLE freereply (
	    rno INT PRIMARY KEY AUTO_INCREMENT,
	    bno INT REFERENCES freeboard (bno) ON DELETE CASCADE,
	    reply VARCHAR(1000),
	    reply_id VARCHAR(50),
	    reply_pw VARCHAR(50),
	    reply_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	    update_date DATETIME DEFAULT NULL
	);
 */

@Getter @Setter @ToString
public class ReplyVO {

	private int rno, bno;
	private String reply, replyId, replyPw;
	private LocalDateTime replyDate, updateDate;
	
}
