package com.nutriTrack.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriTrack.entity.DietRecord;
import com.nutriTrack.repository.DietRecordRepository;
import com.nutriTrack.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 리포지트리 자동 연결
@Transactional(readOnly = true) 
public class DietRecordService {

	private final DietRecordRepository dietRecordRepository;
	
	public Long saveDietRecord(DietRecord dietRecord) {

		
		
		DietRecord saveDietRecord = dietRecordRepository.save(dietRecord); 
		return saveDietRecord.getDietID();
		
	}
	public List<DietRecord> getDietRecords(){
		dietRecordRepository.findByUser();
	}
}
