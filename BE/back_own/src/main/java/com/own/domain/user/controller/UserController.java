package com.own.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.own.domain.user.dto.request.UserSignupRequest;
import com.own.domain.user.dto.request.UserUpdateRequest;
import com.own.domain.user.dto.response.UserResponse;
import com.own.domain.user.dto.response.UserTierResponse;
import com.own.domain.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<Boolean> signup(@RequestBody UserSignupRequest user) {
		
		boolean result = userService.signupUser(user);
		
		return ResponseEntity.ok(result);
	}
	
	// 프로필 조회
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable int userId) {
		
		UserResponse response = userService.getUserProfile(userId);
		
		return ResponseEntity.ok(response);
	}
	
	// 프로필 수정
	@PutMapping("/{userId}")
	public ResponseEntity<Boolean> update(@PathVariable int userId, @RequestBody UserUpdateRequest user) {
		
		boolean result = userService.deleteUser(userId);
		
		return ResponseEntity.ok(result);
	}
	
	// 회원탈퇴
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> delete(@PathVariable int userId){
		
		boolean result = userService.deleteUser(userId);
		
		return ResponseEntity.ok(result);
	}
	
	// 유저 등급 조회
	@GetMapping("/{userId}/tier")
	public ResponseEntity<UserTierResponse> getTier(@PathVariable int userId) {
		
		UserTierResponse tier = userService.getUserTier(userId);
		
		return ResponseEntity.ok(tier);
	}

}
