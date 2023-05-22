package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/db-config.xml")
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	
	@Test
	@DisplayName("회원가입을 진행했을 때 회원가입에 성공해야 한다.")
	void registerTest() {
		UserVO vo = new UserVO();
		vo.setUserId("abc1234");
		vo.setUserPw("aaa1111!");
		vo.setUserName("홍길동");
		
		mapper.join(vo);
	}
	
	@Test
	@DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴되어야 한다.")
	void idCheckTest() {
		String id = "abc1234";
		
		assertEquals(1, mapper.idCheck(id));
	}
	
	@Test
	@DisplayName("존재하는 회원 아이디와 올바른 비밀번호를 입력했을 시 회원의 정보가 리턴되어야 한다.")
	void loginTest() {
		String id = "abc1234";	
		assertNotNull(mapper.login(id));
	}
	
	@Test
	@DisplayName("존재하지 않는 회원의 아이디를 입력하면 null이 올 것이다.")
	void getInfo() {
		PageVO paging = new PageVO();
		UserVO vo = mapper.getInfo("abc1234", paging);
		log.info(vo.toString());
		
//		assertNull(mapper.getInfo("abc1233"));
	}
	
	@Test
	@DisplayName("ID를 제외한 회원의 정보를 수정할 수 있다.")
	void updateUser() {
		UserVO vo = new UserVO();
		vo.setUserId("abc1234");
		vo.setUserPw("abc1234");
		vo.setUserName("홍수정");
		vo.setUserPhone1("010");
		vo.setUserEmail1("aass");
		
		mapper.updateUser(vo);
		
//		assertEquals(mapper.getInfo("abc1234").getUserName(), vo.getUserName());
	}

}

