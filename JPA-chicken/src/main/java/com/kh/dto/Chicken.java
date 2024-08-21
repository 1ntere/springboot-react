package com.kh.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity//MySQL에 테이블이 존재하지 않으면 테이블 생성
@Getter
@Setter//만약 ctrl+space를 했을 때 lombok이 뜨지 않으면
//build.gradle의 dependencies에 lombok 관련 설정이 있는지 확인
public class Chicken {
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String chickenName;
	private String description;
	private double price;//소수점을 고려
	
	
	
}
