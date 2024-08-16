package com.kh.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //MySQL과 매칭
public class BCUser {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 id값 올라가는 시퀀스
	                                                    //NEXTVAL, Auto increment와 동일
	private int id;
	private String username;
	private String email;
	private String password;
}
