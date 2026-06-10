package com.nutriTrack.repository;

import org.springframework.stereotype.Repository;

import com.nutriTrack.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// 1. 이메일로 유저를 찾아내기 (로그인이나 중복 검사에서 필요함) 
	Optional<User> findByEmail(String email);
	
	// 2. 닉네임으로 유저 찾기 (닉네임은 겹치지 않게 중복 체크 하기)
	Optional<User> findByNickName(String nickname);
	
	// 3. 특정 헬스장 소속 유저들만 찾기 (넣을지 말지 보류)
	
	
}
