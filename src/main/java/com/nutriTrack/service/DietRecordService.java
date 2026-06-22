package com.nutriTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriTrack.entity.DietRecord;
import com.nutriTrack.entity.User;
import com.nutriTrack.repository.DietRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 리포지트리 자동 연결
@Transactional(readOnly = true) 
public class DietRecordService {

	private final DietRecordRepository dietRecordRepository;
	
	@Transactional
	public Long saveDietRecord(DietRecord dietRecord) {

		
		
		DietRecord saveDietRecord = dietRecordRepository.save(dietRecord); 
		return saveDietRecord.getId();
		
	}
	public List<DietRecord> getDietRecords(User user){

		
		List<DietRecord> userDietRecord = dietRecordRepository.findByUser(user);
		return userDietRecord;
	}
}
