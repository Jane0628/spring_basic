package com.spring.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 	CREATE TABLE freeboard (
	    bno INT PRIMARY KEY AUTO_INCREMENT,
	    title VARCHAR(300) NOT NULL,
	    writer VARCHAR(50) NOT NULL,
	    content VARCHAR(3000) NOT NULL,
	    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	    update_date DATETIME DEFAULT NULL
	);
 */

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardVO {
	
	private int bno;
	private String title, writer, content;
	private LocalDateTime regDate, updateDate;
	
	// 게시글의 댓글 개수 표현을 위한 변수 선언
	private int replyCnt;

}
