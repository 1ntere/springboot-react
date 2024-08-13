package com.kh.service;

import org.apache.ibatis.annotations.Param;

import com.kh.dto.NaverUser;

public interface LoginService {
	NaverUser login(String id, String password);
}
