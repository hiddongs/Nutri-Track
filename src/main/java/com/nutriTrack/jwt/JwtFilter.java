package com.nutriTrack.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			
			String token = authHeader.substring(7);
			
			// 토큰 유효성 검증
		    if(jwtUtil.validateToken(token)) {
		    	// 유효하면 다음 필터
		        Long userId = jwtUtil.getUserId(token);
		        UsernamePasswordAuthenticationToken authentication = 
		        		new UsernamePasswordAuthenticationToken(userId, null, List.of());
		        
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        
		    	filterChain.doFilter(request,response);
		    }
		    else {
		    	// 유효하지 않으면 401
		    	response.setStatus(401);
		    }
		}else {
		   // 토큰이 없으면 다음 필
           filterChain.doFilter(request, response);
		}
	}

}
