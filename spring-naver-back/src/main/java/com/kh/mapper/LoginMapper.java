package com.kh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.dto.NaverUser;

@Mapper
public interface LoginMapper {
	NaverUser login(@Param("id") String id, @Param("password") String password);
}
