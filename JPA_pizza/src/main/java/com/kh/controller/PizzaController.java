package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.entity.Kh_pizza;
import com.kh.service.PizzaService;

// 모두 api/pizza 컨트롤러 완성
// 검색은 api url /search
@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	
	
	@GetMapping
	public List<Kh_pizza> getAllPizza(){
		return pizzaService.getAllPizza();
	}
	
	@PostMapping
	public Kh_pizza savePizza(@RequestBody Kh_pizza p) {
		return pizzaService.createPizza(p);
	}
	
	@GetMapping("/search")
	public List<Kh_pizza> searchPizza(@RequestParam("query") String query) {
		return pizzaService.searchPizza(query);
	}
}














