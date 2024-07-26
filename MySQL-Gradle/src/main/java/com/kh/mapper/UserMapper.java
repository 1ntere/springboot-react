package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.User;

@Mapper
public interface UserMapper {
	List<User> findAll();
	void insertUser(User user);
}
