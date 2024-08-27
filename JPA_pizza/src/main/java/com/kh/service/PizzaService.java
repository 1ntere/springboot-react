package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.entity.Kh_pizza;
import com.kh.repository.PizzaRepository;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepository pizzaRepository;
	
	// 피자 메뉴 추가하기
	public Kh_pizza createPizza(Kh_pizza p) {
		return pizzaRepository.save(p);
	}
	// 피자 메뉴 모두보기
	public List<Kh_pizza> getAllPizza(){
		return pizzaRepository.findAll();
	}
	
	// 피자 검색하기
	public List<Kh_pizza> searchPizza(String query) {
		return pizzaRepository.findByNameContainingIgnoreCase(query);
	}
}









