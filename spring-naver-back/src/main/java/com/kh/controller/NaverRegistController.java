package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.NaverUserService;

//네이버로 회원가입 후 DB에 네이버 정보를 등록하는 컨트롤러
@RestController
public class NaverRegistController {
	@Autowired
	private NaverUserService naverUserService;
	
	//회원가입을 위한 PostMapping 작성
	//@PostMapping("서울특별시/강남구/역삼역3번출구")
	@PostMapping("/NaverAPI/register")//프론트와 데이터를 주고받기 위한 만남의 장소, API 또는 URL로 부름
	public String insertNaverUser(@RequestBody NaverUser naverUser) {
		//DB에 React로 가져온 naverUSer 정보를 큰 수정없이 전체 다 넣겠다.
		naverUserService.insertNaverUser(naverUser);
		
		//naverUserService.insertNaverUser(null);
		//null이 들어갈 자리에는 React에서 받아온 값을 넣어주는 공간
		//처음에는 Java에서 어떤 값을 넣어줘야하는지 모르기 때문에 null로 설정이 되어있는 것일 뿐
		//null 자리에는 @RequestBody나 @RequestParam으로 가져온 값을 작성
		//@RequestBody = 전체 (=전체를 한 번에 집어넣는다는 것은 부분적으로 수정하거나 커스텀이 필요하지 않은 경우)
		//@RequestPAram = 부분 수정 추가, 부분적으로 추가할 때 사용
		
		return "Naver API를 활용한 회원가입 성공!!";
	}
}
