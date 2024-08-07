package com.kh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//React : 3000 포트 SpringBoot : 백엔드포트
//온전히 제대로 연결해줄 수 있도록 설정
//WebSocket : 프론트와 백엔드가 서로 상호작용을 주기적으로 진행할 때
//좀 더 안전하게 연결을 계속 진행하겠다 설정

@Configuration //설정
public class WebConfig implements WebMvcConfigurer{
	//이미지폴더경로를 react가 가져갈 수 있도록 허용
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry r) {
		r.addResourceHandler("/images/**")
			//http://localhost:9008/images/~ 로 들어오는 모든 경로 허용
		 .addResourceLocations("file:C:/Users/user1/Desktop/saveImage/");
		 	//바탕화면에 지정한 이미지 경로 넣어주기("file: + application.properties에 저장한 경로 + /")
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		        .allowedOrigins("http://localhost:3000")
		        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		        .allowedHeaders("*");
	}
	
	/*
	 * .allowedOrigins("http://localhost:3000") : 이 주소로
	 * .addMapping("/**") : 3000번 뒤에 오는 모든 url api 주소를 모두 허용
	 * 
	 * .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	 *                  보기     입력     수정      삭제     기타 등등
	 * http://localhost:3000/** 들어오는 모든 요청(GET, POST, PUT, DELETE, 기타 등등) 허용
	 * .allowedHeaders("*") : <html> <head> 정보에 들어갈 모든 요청 OK!
	 * */
}
