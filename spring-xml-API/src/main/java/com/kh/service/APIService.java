package com.kh.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class APIService {
	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${api.content-type}")
	private String contentType;
	
	public String getAirData() throws Exception {
		//주소값 설정
		String url = baseUrl;
		url += "?serviceKey=" + URLEncoder.encode(apiKey, "UTF-8");
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		url += "&returnType=xml";//서비스키와 서울지역 데이터를 가져올 때 xml 파일로 가져옴
		//xml : 구버전, json : 구버전, 신버전
		
		/* Header */
		//세팅된 주소를 가지고 데이터 가져오기
		System.out.println("(1) url : " + url);
		URL requestUrl = new URL(url);
		System.out.println("(2) requestUrl : " + requestUrl);
		HttpURLConnection uc = (HttpURLConnection) requestUrl.openConnection();
		uc.setRequestMethod("GET");
		uc.setRequestProperty("content-Type", contentType);
		//requestUrl : URL 주소값 형식
		//HttpUrlConnection : java에서 특정 주소에 연결과 동시에 HttpMethod 요청을 보낼 수 있음
		//HttpMethod : GET, POST, PUT, DELETE
		
		/* Body */
		//남의 주소에서 남이 지정한 형식을 가져와야 하기 때문에 한 줄씩 읽어서 모두 실시간 가져오기
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		//데이터를 한 줄 씩 가져오기
		while ((line = br.readLine()) != null) {
			response.append(line);
		}
		
		br.close();//데이터 다 가져오면 담기
		uc.disconnect();//URL 연결 끊기
		
		//가져온 데이터 글자 값으로 보여주기위해 전달
		return response.toString();
	}
	
	
}
