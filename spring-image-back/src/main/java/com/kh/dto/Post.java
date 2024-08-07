package com.kh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int id;
	private String title;
	private String content;
	private String imageUrl;
	//id와 createdAt 같은 경우에는 MySQL에서 자동으로 숫자와 날짜 생성을 해주기 때문에
	//mapper.xml에 작성하지 않음
	private String createdAt;//게시판에 작성한 글과 이미지가 MySQL에 들어온 시간
}
