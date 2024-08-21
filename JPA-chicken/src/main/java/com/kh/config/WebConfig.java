package com.kh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//3000 다음에 오는 API URL 모두 허용
                .allowedOrigins("http://localhost:3000")//localhost:3000 주소 접속 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")//HttpMethod와 기타 옵션 모두 허용
                .allowedHeaders("*");//데이터, 이미지, 파일, 다중 파일 등 모두 허용
	}

}
