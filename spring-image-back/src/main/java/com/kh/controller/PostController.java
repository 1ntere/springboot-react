package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.service.PostService;

@RestController
public class PostController {
	@Autowired
	private PostService postService;
	
	//이미지 저장하기 위한 PostMapping
	@PostMapping("/gallery/upload")
	//@ResponseEntity : 데이터가 무사히 전달되고 있는지 체크
	public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile[] files,
			                                   @RequestParam("title") String title,
			                                   @RequestParam("content") String content) {	
		postService.uploadImages(files, title, content);
		return ResponseEntity.ok("이미지 DB 업로드 성공");
	}
	
	@GetMapping("/posts")//DB에 저장된 게시글 내용, 이미지 가져오기
	public ResponseEntity<List<Post>> findAll() {
		               //<> : 객체를 구분하는 구분 괄호
		//postService.findAll();
			//데이터가 무수히 많아 목록으로 가져와야 하는데
			//리스트로 전달을 안해주면 map <-> react 사이에 문제가 발생
		List<Post> posts = postService.findAll();
		return ResponseEntity.ok(posts);
	}
}
