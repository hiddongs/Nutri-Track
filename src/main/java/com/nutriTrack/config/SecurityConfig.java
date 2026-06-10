package com.nutriTrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티를 활성화
public class SecurityConfig {

	
	/*
	 * PasswordEncoder를 스프링(Bean) 으로 등록
	 * 이 코드가 있으면 UserService에서 @RequireArgsConstructor로 가져올 수 있음
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		// 표준화된 가장 많이 쓰는 암호화 알고리즘 사용
		return new BCryptPasswordEncoder();
		
	}
	
	/*
	 * 시큐리티 방화벽 설정
	 * 처음 개발할 때는 로그인 창이 뜨거나 주소가 막히면 번거롭다
	 * 당장은 모든 주소로 접근 허용
	 * 
	 */
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
        .csrf(csrf -> csrf.disable()) // 앱(React Native)과 통신할 때 필수 설정
        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // 일단 모든 API 주소 요청을 허용합니다.
        );
        
    return http.build();
	}
}
