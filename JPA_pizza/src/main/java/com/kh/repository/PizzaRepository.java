package com.kh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.entity.Kh_pizza;

// mapper와 mybatis를 합쳐놓은 기능 기본으로 select insert  특정단어 찾기는 
// JpaRepository 안에 작성이 되어있기 때문에
// 검색과 같이 특수한 작업만 작성
// mapper와 같이 interface임 
// 매퍼와 다른 점은 mybatis와 같은 기능을 포함한 interface
@Repository
public interface PizzaRepository extends JpaRepository<Kh_pizza, Integer> {
	// 검색과 같이 보는 것이 여러 개 일 때는 리스트!
	// find = where  어떤 컬럼 기준으로 검색을 들어갈거니? 피자명으로 검색하겠다. = name
	// Containing = Like % %  특정 단어 앞 뒤로 상관없이 검색
	// Like %name   = 문자열 끝이  찾고자하는 name으로 끝나는 모든 값 찾기
	// Like %name%  = 문자열 중간에 찾고자하는 name 이 들어가는 모든 값 찾기
	// Like name%   = 문자열 시작이 찾고자하는 name으로 시작하는 모든 값 찾기
	// IgnoreCase = 대소문자 구분x
	List<Kh_pizza> findByNameContainingIgnoreCase(String query);
}












