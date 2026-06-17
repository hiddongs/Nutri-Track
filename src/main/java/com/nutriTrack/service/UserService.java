package com.nutriTrack.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriTrack.entity.User;
import com.nutriTrack.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 리포지트리 자동 연결
@Transactional(readOnly = true) // 읽기 전용으로 만들어 성능 최적화
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 도구
	
	/*
	 * 회원가입 기능
	 */
	
	@Transactional // (INSERT를 위해 readOnly 해제)
    public Long join(User user) {
		
		// 중복 이메일 검증
		validateDuplicateUser(user.getEmail());
		
		
		// 같은 이메일을 검증했을 때
		Optional<User> findNickName = userRepository.findByNickName(user.getNickName());
		
		if(findNickName.isPresent()) { // 상자 안에 유저 데이터가 진짜 들어있다면( 중복이라 판단 )
		   throw new IllegalArgumentException("이미 사용 중인 닉네임입니다");
			
		}
		
		// 비밀번호 암호화 과정
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		User newUser = User.builder()
				.name(user.getName())
				.email(user.getEmail())
		        .password(encodedPassword)      
		        .nickName(user.getNickName())
		        .gender(user.getGender())
		        .age(user.getAge())
		        .height(user.getHeight())
		        .weight(user.getWeight())
		        .activityLevel(user.getActivityLevel())
		        .targetPurpose(user.getTargetPurpose())
		        .created_at(LocalDateTime.now()) 
		        .build();
		
		User savedUser = userRepository.save(newUser);
		return savedUser.getUserID(); 
	}
	private void validateDuplicateUser(String email) {
		Optional<User> findUser = userRepository.findByEmail(email);
	    if (findUser.isPresent()) {
	        throw new IllegalStateException("이미 사용 중인 이메일입니다");
	    }
		
	}
	public User findOne(Long userId) {
		// TODO Auto-generated method stub
		
		// DB에서 ID로 유저 검색해 상자를 담아온
		Optional<User> findUser = userRepository.findById(userId);
		
		
		if(findUser.isPresent()) {
			
			return findUser.get(); // 꺼내서 돌려줌
			
		}else {
			
			// 유저기 없다면 에러 발생
			throw new IllegalArgumentException("존재하지 않는 유저입니다");
			
		}
	}
		
	
	public User login(String email, String password) {
		
		  Optional<User> findUserEmail = userRepository.findByEmail(email);
		  
		  
		  if(findUserEmail.isPresent()) {
			  
			  boolean isMatch = passwordEncoder.matches(password,findUserEmail.get().getPassword());
			  if(!isMatch) {
				  throw new IllegalArgumentException("비밀번호가 틀렸습니다");
			  }
			  return findUserEmail.get();
		  }
		  else {
			  throw new IllegalArgumentException("존재하지 않는 유저입니다.");
		
		}
	}
}
