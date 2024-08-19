package com.kh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//공공데이터 API를 이용한 API URL 주소값 한 번 더 확인

//http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty
//공공데이터 주소	: http://apis.data.go.kr -> react에서 env로 80 처리를 한 주소
//내 컴퓨터 주소	: http://localhost:8081 -> react에서 env로 80 처리를 안해준 것

@RestController
@RequestMapping("/B552584/ArpltnInforInqireSvc")//공공데이터에서 대기오염서비스 공통 주소
//만약에 대기오염서비스가 아니라 수질오염서비스 주소를 이용해야 한다면
//RequestMapping에 /수질오염서비스API를 작성해주면 됨 @RequestMapping("/수질오염서비스로이동하기")
public class APISController {
	//상세기능 3. 시도별 실시간 측정 정보 조회 API
	@GetMapping("/getCtprvnRltmMesureDnsty")//시도별 실시간 측정 정보 조회 API 주소
	public String get시도별실시간측정정보() {
		return "시도별측정결과전달하기";
	}
	
	//상세기능 1. GetMapping으로 측정소별 실시간 측정정보 조회 API
	@GetMapping("/getMsrstnAcctoRltmMesureDnsty")
	public String get측정소별실시간측정정보() {
		return "측정소별측정결과전달하기";
	}
	//상세기능 2. GetMapping으로 통합대기환경지수 나쁨이상 측정소 목록 조회 API
	@GetMapping("/getUnityAirEnvrnIdexSnstiveAboveMsrstnList")
	public String get나쁨이상측정소목록() {
		return "나쁨이상측정소목록전달하기";
	}
	//상세기능 4. GetMapping으로 대기질 예보통보 조회 API
	@GetMapping("/getMinuDustFrcstDspth")
	public void 예보통보조회() {
		System.out.println("측정결과 전달하기");
	}
	//상세기능 5. GetMapping으로 초미세먼지 주간예보 조회 API
	@GetMapping("/getMinuDustWeekFrcstDspth")
	public void 주간예보조회() {
		System.out.println("측정결과 전달하기");
	}
	
}
