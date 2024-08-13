package com.kh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.UserProfile;

public interface ProfileService {
	List<UserProfile> getProfile();
	void insertProfile(UserProfile userProfile);
	void uploadProfile(MultipartFile[] files, String username);
}
