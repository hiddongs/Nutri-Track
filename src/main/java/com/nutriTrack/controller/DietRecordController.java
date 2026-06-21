package com.nutriTrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriTrack.entity.DietRecord;
import com.nutriTrack.service.DietRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diet")
public class DietRecordController {
	
	private final DietRecordService dietRecordService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveDiet(@RequestBody DietRecord dietRecord){
		
		
		return null;
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getDietRecords(@PathVariable Long userId){
		return null;
		
	}

}
