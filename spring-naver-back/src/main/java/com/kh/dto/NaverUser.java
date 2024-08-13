package com.kh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor//기본생성자
@AllArgsConstructor//필수생성자
@ToString
public class NaverUser {
	//id ~ profileImage 는 네이버에 저장된 값을 가져와서 DB에 저장
	private String id;
	private String email; 
	private String nickname;
	private String name;
	private String gender;
	private String profileImage;
	//password는 사용자가 작성한 비밀번호를 가져와서 DB에 저장
	private String password;
}
