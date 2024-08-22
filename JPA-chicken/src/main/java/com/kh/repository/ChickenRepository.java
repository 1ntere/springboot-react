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
	
	//select * from chicken
	
	//특정 값을 찾을 때 쓰는 기능
	//findById(Integer id); => where 대신 find
	//	만약에 where 이메일 = '', 비밀번호 = '' 로그인을 한다면
	//	레포지토리에서 findByEmailPassword => where절
}
