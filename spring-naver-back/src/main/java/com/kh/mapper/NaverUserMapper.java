package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.NaverUser;

//Mybatis에 id 값으로 작성한 sql 기능 목록을 작성
//기능에 대한 목록만 보기 때문에 interface
@Mapper
public interface NaverUserMapper {
	//List<NaverUser> findAll();
	
	//네이버 SNS 연동해서 회원가입하는 insert
	void insertNaverUser(NaverUser user);
}
