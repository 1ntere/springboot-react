package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.User;

//Mybatis에 id 값으로 작성한 sql 기능 목록을 작성
//기능에 대한 목록만 보기 때문에 interface
@Mapper
public interface UserMapper {
	List<User> findAll();
	
	void insertUser(User user);
	
	void deleteUser(int id);
	
	void updateUser(User user);
}
