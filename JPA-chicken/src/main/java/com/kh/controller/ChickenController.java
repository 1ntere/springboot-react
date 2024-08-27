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

import com.kh.dto.Chicken;
import com.kh.service.ChickenService;

@RestController
@RequestMapping("/api/chicken")
public class ChickenController {
	@Autowired//참조
	private ChickenService chickenService;
	
	//리스트를 모두보기
	@GetMapping
	public List<Chicken> getAllChickens() {
		return chickenService.getAllChickens();
	}
	
	//추가하기
	@PostMapping
	public Chicken saveChicken(@RequestBody Chicken chicken) {
		return chickenService.createChicken(chicken);
	}
	
	//하나하나상세보기
	@GetMapping("{id}")
	public Chicken getChickenByID(@PathVariable("id") Integer id) {
		return chickenService.getChickenById(id);
	}
	
	//수정하기
	@PutMapping("{id}")
	public Chicken updateChicken(@PathVariable("id") Integer id,
			                     @RequestBody Chicken chicken) {
		System.out.println("chicken data : " + chicken);
		return chickenService.updateChicken(id, chicken);
	}
	
	//삭제하기
	@DeleteMapping("{id}")
	public void deleteChicken(@PathVariable("id") Integer id) {
		chickenService.deleteChicken(id);
	}
	
	//검색기능 GET
	@GetMapping("/search")
	public List<Chicken> searchChickens(@RequestParam("query") String query) {
		return chickenService.searchChickens(query);
	}
	
}
