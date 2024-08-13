package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.NaverUser;
import com.kh.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public NaverUser login(String id, String password) {
		return loginMapper.login(id,  password);
	}
}
