package com.kh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor//기본생성자
@AllArgsConstructor//필수생성자
@ToString
public class User {
	private int id;
	private String name;
	private String email;
}
