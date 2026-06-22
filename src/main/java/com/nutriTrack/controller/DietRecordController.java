package com.nutriTrack.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriTrack.entity.DietRecord;
import com.nutriTrack.entity.User;
import com.nutriTrack.jwt.JwtUtil;
import com.nutriTrack.service.DietRecordService;
import com.nutriTrack.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/diet")
public class DietRecordController {
	
	private final DietRecordService dietRecordService;
	private final JwtUtil jwtUtil;
	private final UserService userService;
	@PostMapping("/save")
	public ResponseEntity<?> saveDiet(@RequestBody DietRecord dietRecord, @RequestHeader("Authorization") String authHeader){
		
		
	   String token = authHeader.substring(7);
	   
	   Long userId = jwtUtil.getUserId(token);
	   User user = userService.findOne(userId);
	   try {
	   DietRecord recordWithUser = DietRecord.builder()
			   .user(user)
			   .foodName(dietRecord.getFoodName())
			   .calorie(dietRecord.getCalorie())
			   .carb(dietRecord.getCarb())
			   .protein(dietRecord.getProtein())
			   .fat(dietRecord.getFat())
			   .amount(dietRecord.getAmount())
			   .mealType(dietRecord.getMealType())
			   .date(dietRecord.getDate())
			   .build();
			   
	   Long saveRecord = dietRecordService.saveDietRecord(recordWithUser);
		return new ResponseEntity<>(saveRecord,HttpStatus.OK);
	   }catch(Exception e) {
		   return new ResponseEntity<>("서버 오류가 발생했습니다",HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getDietRecords(@PathVariable("userId") Long userId){
		
		try {
			
			
			User findUser = userService.findOne(userId);
			List<DietRecord> recordList = dietRecordService.getDietRecords(findUser);
			return ResponseEntity.ok(recordList);
			
		}
		catch(Exception e){
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}

}
