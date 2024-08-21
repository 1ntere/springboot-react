package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.dto.Chicken;

//MyBatis - mapper 이 2가지를 설정
@Repository
//@Repository와 @Mapper는 interface로 시작
public interface ChickenRepository extends JpaRepository<Chicken, Integer> {
	//전체보기, 전체넣기, 전체수정하기, 전체삭제하기와 같은 기본 기능은
	//JpaRepository 안에 모두 들어있음
	
}
