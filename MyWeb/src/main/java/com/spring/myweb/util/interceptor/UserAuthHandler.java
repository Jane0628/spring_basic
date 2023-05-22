package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthHandler implements HandlerInterceptor {

	// 회원 권한이 필요한 페이지 요청이 들어왔을 때, 컨트롤러보다 먼저 요청을 가로채서 확인하는 용도로 사용될 예정.
	// 글쓰기 화면, 마이페이지 화면으로 들어가는 요청을 가로채서 검사하도록 합시다.
	// 권한이 없다면 로그인 페이지로 보내버리겠습니다.
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 세션에서 로그인 데이터를 얻은 후 확인해보겠습니다.
		if(request.getSession().getAttribute("login") == null) {
			// 로그인 안함
			response.sendRedirect(request.getContextPath() + "/user/userLogin");
			// false가 리턴되면 컨트롤러로 요청이 전달되지 않습니다.
			return false;
		}
		
		// 로그인을 했다면 통과!
		return true;
	}
	
	
}
