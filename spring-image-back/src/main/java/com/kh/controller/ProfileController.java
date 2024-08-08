package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.UserProfile;
import com.kh.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	/*
	@Autowired
	와
	public ProfileController(ProfileService profileService) {
		this.service = profileService;
	}
	는 동일
	*/
	private ProfileService service;
	//private ProfileServiceImpl profileServiceImpl;
	
	@GetMapping("/watching")
	public ResponseEntity<List<UserProfile>> getProfile() {
		return ResponseEntity.ok(service.getProfile());
	}
	
	/*
	★ 오류 : parameter Type error
	★ 원인 : @RequestParam 안에 React에서 값을 가져올 때 값을 가져온 변수명을 작성하지 않으면 에러 발생
	★ 에러가 발생한 코드
	public ResponseEntity<String> uploadProfile(@RequestParam MultipartFile[] files,
			                                    @RequestParam String username,
			                                    @RequestParam String profileImageUrl) {
	★ 해결방법 : @RequestParam 안에 React에서 값을 가져올 때 값을 가져온 변수명을 작성
	★ 해결한 코드
	public ResponseEntity<String> uploadProfile(@RequestParam("files") MultipartFile[] files,
			                                    @RequestParam("username") String username,
			                                    @RequestParam("profileImageUrl") String profileImageUrl) {
	*/
	@PostMapping("/upload")
	public ResponseEntity<String> uploadProfile(@RequestParam("files") MultipartFile[] files,
			                                    @RequestParam("username") String username,
			                                    @RequestParam("profileImageUrl") String profileImageUrl) {
		 //ResponseEntity<String> : 보통 <String> 이면 하나하나씩 넣어주기 때문에 @RequestParam
		
		service.uploadProfile(files, username, profileImageUrl);
		return ResponseEntity.ok("이미지 업로드 성공");
	}
}
