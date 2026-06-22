package com.nutriTrack.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;

@Component // 스프링 빈 등
public class JwtUtil {
	
	private final String SECRET_KEY = "nutritrack-secret-key-2024-very-long-string";
	byte[] byteArray = SECRET_KEY.getBytes(); // 문자열을 바이트 배열로,JWT 서명 알고리즘은은 문자열이 아닌 바이트 배열로 다루기 때문
	
	public String generateToken(Long userId) {
		
	
		
		return Jwts.builder() // JWT 토큰 생성 빌더 패턴, 체이닝으로 객체 조
				.subject(String.valueOf(userId)) // 토큰의 주인을 지정 문자열 기반이라 파싱
		        .issuedAt(new Date()) // 토큰 발급 시간 기록
		        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 토큰 만료 시간 밀리초, 분, 시간, 
		        .signWith(Keys.hmacShaKeyFor(byteArray)) // 토큰에 서명하는 부분 (위조를 확인하기 위해)
		        .compact(); // 토큰을 최종적으로 문자열 변
	
		
	}
	
	public Long getUserId(String token) {
	
	   return Long.parseLong(
	   Jwts.parser()
	       .verifyWith(Keys.hmacShaKeyFor(byteArray))
	       .build()
	       .parseSignedClaims(token)
	       .getPayload()
	       .getSubject()
	   );

	}
	
	// 토큰 유효성 검사
	public boolean validateToken(String token) {
		
	try {
		
		Jwts.parser()
		    .verifyWith(Keys.hmacShaKeyFor(byteArray))
	        .build()
	        .parseSignedClaims(token);
		
		return true;
		
	} catch(Exception e) {
		
		
		return false;
	}
	}

 
}
