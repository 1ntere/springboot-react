package com.kh.config;
//React 3000에서 접속할 수 있도록 설정

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//3000 뒤에 붙는 url 모두 허용
		        .allowedOrigins("http://localhost:3000")//3000 OK
		        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")//url 모든 동작 OK
		        .allowCredentials(true);//쿠키나 세션과 같은 자격 허용
	}
}
