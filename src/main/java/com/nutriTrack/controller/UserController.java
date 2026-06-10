package com.nutriTrack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nutriTrack.entity.User;
import com.nutriTrack.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users") // 매핑 요청, 이 컨트롤러로 시작하는 주소는 /api/users로 시작

public class UserController {

	private final UserService userService;
	
	/*
	 * 회원가입 API (POST 요청 처리)
	 * 프론트에서 보낸 유저 정보를 가져와서 DB에 저장하는 
	 */
	
	@PostMapping("/join")
	public ResponseEntity<String> registerUser(@RequestBody User user){
		try {
			
			// 회원가입 메서드를 호출하여 회원가입 진행
			Long saveId = userService.join(user);
			
			// 성공하면 메시지 출력과 함께 응답
			return new ResponseEntity<>("회원가입 성공 !!! ID : " + saveId, HttpStatus.CREATED);
			
			
		}catch(IllegalStateException e) {
			
			// 중복 이메일을 감지하면 에러코드 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
			
		}catch(IllegalArgumentException e) {
			
			// 중복 닉네임을 감지하면 에러코
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}catch(Exception e) {
			
			// 그 외의 여러 에러가 발생하면 에러 응답 출
			return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * 내 정보를 조회하는 API (GET 요청 처리)
	 * 주소창에는 /api/users/1 이라고 치면 1번 유저의 정보를 보여준
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserInfo(@PathVariable("id") Long userId){
		try {
			
			// 서비스에서 유저를 찾기
		    User findUser = userService.findOne(userId);
		    
		    // 만약 유저를 찾으면 유저 데이터 전
		    return new ResponseEntity<>(findUser, HttpStatus.OK);
		    
		}catch (IllegalArgumentException e) {
			// 없으면 에러 코드
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
}
