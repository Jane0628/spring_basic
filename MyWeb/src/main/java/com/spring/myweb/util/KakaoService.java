package com.spring.myweb.util;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.spring.myweb.command.KakaoUserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaoService {
	
	@Value("${kakao.clientId}")
	private String clientId;
	
	@Value("${kakao.clientSecret}")
	private String clientSecret;
	
	@Value("${kakao.redirectUri}")
	private String redirectUri;
	
	private String sessionState = "kakao_oauth_state";

	// 카카오 아이디로 로그인 인증 URL 생성
	public String getAuthorizationUrl(HttpSession session) {
		
		// 세션 유효성 검증을 위하여 난수를 생성
		String state = UUID.randomUUID().toString();
		
		// 생성된 난수값을 session에 저장
		session.setAttribute(sessionState, state);
		
		// state는 필수적이진 않은 값이지만 보내는 것을 추천한다. (보안)
		// 우리가 난수를 생성해서 카카오 서버쪽에 보내주면 카카오는 해당 난수를 그대로 다시 나에게 보내준다.
		// 때문에 내 Rest API 키가 털려도 보낸 사람이 내가 맞는지 확인이 가능하다.		
		return String.format("https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=%s", clientId, redirectUri, state);
	}

	// 카카오 아이디로 CallBack 처리 및 AccessToken 획득
	public String getAccessToken(HttpSession session, String code, String state) {
		log.info("getAccessToken 호출!");
		
		// 요청 uri
		String requestUri = "https://kauth.kakao.com/oauth/token";
		
		// CallBack으로 전달받은 세션 검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인
		String sessionValue = (String) session.getAttribute(sessionState);
		
		if(sessionValue.equals(state)) { // 로그인할 때 만든 state와 성공 후 응답된 state가 일치한다면?
			
			// 요청 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			// 카카오 서버 쪽에서 설정해달라고 한 content-type 설정하기.
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// 요청 파라미터 설정
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", clientId);
			params.add("redirect_uri", redirectUri);
			params.add("code", code);
			params.add("client_secret", clientSecret);
			
			// 카카오 서버로 rest 방식의 post 통신을 보내줄 객체 생성.
			RestTemplate template = new RestTemplate();
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(params, headers);
			
			// 통신을 보내면서 응답 데이터를 바로 리턴.
			// param 1 : 요청 uri
			// param 2 : 요청 방식(method)
			// param 3 : 헤더와 요청 파라미터 정보 엔티티 객체
			// param 4 : 응답 데이터를 받을 객체의 타입 (ex : vo, String, map...)
			ResponseEntity<Map> responseEntity = template.exchange(requestUri, HttpMethod.POST, requestEntity, Map.class);
			
			// 응답 데이터에서 필요한 정보를 가져오자.
			Map<String, Object> responseData = (Map<String, Object>) responseEntity.getBody();
			log.info("토큰 요청 응답 데이터 : {}", responseData);
			
			log.info((String) responseData.get("access_token"));
			return (String) responseData.get("access_token");
			
		} else {
			log.info("state 일치하지 않음!");
			return null;
		}
	}

	// Access Token을 이용하여 카카오 사용자 프로필 API 요청
	public KakaoUserVO getUserProfile(String accessToken) {
		String requestUri = "https://kapi.kakao.com/v2/user/me";
		
		// 요청 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		// GET 방식은 기본이 Content-type: application/x-www-form-urlencoded;charset=utf-8이기 때문에 별도의 설정이 필요가 없다.
		
		// 요청 보내기
		RestTemplate template = new RestTemplate();
		ResponseEntity<KakaoUserVO> responseEntity = template.exchange(requestUri, HttpMethod.GET, new HttpEntity<>(headers), KakaoUserVO.class);
		
		// 응답 바디 읽기.
		KakaoUserVO responseData = responseEntity.getBody();
		log.info("user profile : {}", responseData);
		
		return responseData;
	}
}
