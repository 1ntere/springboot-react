package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.User;
import com.kh.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	/*****불러오기*****/
	@GetMapping//api 주소값 users : 원래는 @GetMapping() 이겠지만, () 생략 가능
	public List<User> findAll() {
		return userService.findAll();
	}
	
	/*****추가하기*****/
	@PostMapping
	public void insertUser(@RequestBody User user) {
		userService.insertUser(user);
	}
	
	/*****삭제하기*****/
/*
	//★ await axios.delete(`/users?id=${id}`);
	@DeleteMapping("/{id}")//삭제를 진행하기 위해 만나는 주소(api)가 /users/유저번호
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
*/
	//★ await axios.delete(`/users`, {params: {id});
	@DeleteMapping//삭제를 진행하기 위해 만나는 주소(api)가 /users, ()는 삭제 가능
	public void deleteUser(@RequestParam("id") int id) {
		userService.deleteUser(id);
	}
	
	/*****수정하기*****/
	@PutMapping//수정
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
}
