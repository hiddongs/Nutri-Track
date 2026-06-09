package com.nutriTrack.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Table(name="User") // 1. User 테이블과 매칭
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User { // 2. 엔티티 클래스로 지정
	
	@Id
	private Long userID; // 아이디
	
	@Column(nullable = false)
	private String name; // 이름
	
	@Column(nullable = false, unique = true, length = 50)
    private String email; // 이메일
    
	@Column(nullable = false)
    private String password; // 비밀번호
    
	@Column(nullable = false, length = 20)
    private String nickName; // 닉네임
    
    
    private Enum gender; // 성별
    
    private int age; // 나이
    
    private double height; // 키
    private double weight; // 몸무게
    
    private Enum actitity_level; // 평소 활동량
    private Enum target_purpose; // 운동 목표(ex. 벌크업, 다이어트, 체중유지)
    
    private String gym_name; // 헬스장 이름
    
	private LocalDateTime created_at; // 가입일
	

	
}
