package com.kh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;

public interface PostService {
	List<Post> findAll();
	
	void insertPost(Post post);
	
	//이미지 업로드를 도와주는 기능 목록 설정
	void uploadImages(MultipartFile[] files, String title, String content);
	
	void updatePost(Post post);
}
