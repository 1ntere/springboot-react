package com.kh.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor//기본 생성자
@AllArgsConstructor//필수 생성자
@ToString //DB에서 값이 제대로 들어왔는지 확인
public class UserProfile {
	private int userId;
	private String username;
	private String profileImageUrl;
	private LocalDateTime createdAt;
}
