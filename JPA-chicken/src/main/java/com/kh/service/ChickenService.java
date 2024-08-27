package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.Chicken;
import com.kh.repository.ChickenRepository;

@Service
public class ChickenService {
	@Autowired
	private ChickenRepository chickenRepository;
	
	//치킨테이블 모두 보기 => 모두 보기는 항상 List
	//List<Chicken> => 목록<주제>
	public List<Chicken> getAllChickens() {
		return chickenRepository.findAll();//find : 찾기, All : 모두
	}
	
	//치킨 메뉴 추가하기
	public Chicken createChicken(Chicken chicken) {
		return chickenRepository.save(chicken);//치킨에 대해서 DTO에 작성된 컬럼들에 모두 삽입
	}

	//치킨 메뉴 상세보기
	public Chicken getChickenById(Integer id) {
		return chickenRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("일치하는 정보를 찾을 수 없습니다."));
	}
	
	//치킨 메뉴 수정하기
	public Chicken updateChicken(Integer id, Chicken uc) {
		                       //Integer id : 수정할 컬럼 아이디
                                   		   //Chicken uc : 수정할 내용, 저장할 치킨 객체
		Chicken chicken = chickenRepository.findById(id)
				          .orElseThrow(() -> new RuntimeException("치킨을 찾을 수 없습니다."));
			//findById를 작성해줄 때는 아이디를 찾지 못할 예외 사항을 필수로 작성해줘야 함
				//.orElseThrow() : 예외사항 작성
		chicken.setChickenName(uc.getChickenName());
			//치킨 객체에 수정된 치킨 이름을 가져와서 넣어주기
		chicken.setDescription(uc.getDescription());
		chicken.setPrice(uc.getPrice());
		return chickenRepository.save(chicken);
	}
	
	//치킨 메뉴 삭제하기
	public void deleteChicken(Integer id) {
		Chicken c = chickenRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("일치하는 정보를 찾을 수 없습니다."));
		chickenRepository.delete(c);
	}
	
	// 치킨 검색 기능 추가
	public List<Chicken> searchChickens(String query) {
		return chickenRepository.findByChickenNameContainingIgnoreCase(query);
	}
}
