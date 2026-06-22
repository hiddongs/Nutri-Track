package com.nutriTrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriTrack.entity.DietRecord;
import com.nutriTrack.entity.User;

@Repository
public interface DietRecordRepository extends JpaRepository<DietRecord, Long>{

	List<DietRecord> findByUser(User user);
	
	
}
