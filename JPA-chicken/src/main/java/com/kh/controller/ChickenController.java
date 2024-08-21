package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.Chicken;
import com.kh.service.ChickenService;

@RestController
@RequestMapping("/api/chicken")
public class ChickenController {
	@Autowired//참조
	private ChickenService chickenService;
	
	@GetMapping
	public List<Chicken> getAllChickens() {
		return chickenService.getAllChickens();
	}
	
	@PostMapping
	public Chicken saveChicken(@RequestBody Chicken chicken) {
		return chickenService.createChicken(chicken);
	}
}
