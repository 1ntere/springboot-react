package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.dto.BCUser;


/*
JpaRepository

MyBatis mapper를 생략해서 작성하는 방법
SQL을 알아서 작성해줌
*/
public interface BCUserRepository extends JpaRepository<BCUser, Integer> {//int 객체 Integer
	//save나 SELECT처럼 특징적으로 무언가를 검색하거나 하지 않는 한 기본적인 SQL은 작성 x
	//BCUser saveUser();
	
	//이메일 찾기 기능
	BCUser findByEmail(String email);
	//SQL => SELECT * FROM BCUser WHERE email = ? ;
}
