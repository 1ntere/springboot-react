package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;

/*
implements로 상속을 해주는 interface 서비스는
기능에 대한 목록이 작성이 되어있을 뿐이지
상세한 기능에 대해서 작성이 된 것이 아님

상속을 받은 class에서
interface 서비스의 목록에 있는 기능들에 대해
설정을 해줘야 하기 때문에
Override를 하나라도 빼먹으면 오류가 남 
*/
@Service
//서비스 기능을 상세하게 작성해주는 공간
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	/*****불러오기*****/
	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}
	
	/*****추가하기*****/
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
		//★ void와 return은 공존 불가능
	}
	
	/*****삭제하기*****/
	@Override
	public void deleteUser(int id) {
		userMapper.deleteUser(id);
	}
	
	/*****수정하기*****/
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
}
