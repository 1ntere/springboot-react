package com.kh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/*
 * 24/07/29 리액트와 스프링
 * OAuthController와 api url 주소가 동일해서 나타는 충돌을 막기위해 
 * //@RequestMapping("/api") 주석을 풀어서 
 * 모든 url 앞에 
 * api가 붙도록 설정
 */
@RestController
@RequestMapping("/naver")//NaverRegist와 주소 충돌을 방지하기 위해 임의로 작성
public class NaverLoginController {
	/*
	@Value : 환경설정 application.properties나 config.properties에 작성한
	         키 이름을 가져오고, 키에 담긴 값을 가져오는 어노테이션
	*/
	
	//application.properties에서 naver.client-id=kK2ZPG2A9hoooAUAnZ9U
	@Value("${naver.client-id}")
	private String clientId;//kK2ZPG2A9hoooAUAnZ9U
	
	//application.properties에서 naver.client-secret=JfT5LWuyGN
	@Value("${naver.client-secret}")
	private String clientSecret;//JfT5LWuyGN
	
	//application.properties에서 naver.redirect-uri=http://localhost:9010/naverLogin
	@Value("${naver.redirect-uri}")
	private String redirectUri;//http://localhost:9010/naverLogin
	
	//application.properties에서 naver.state=RANDOM_STATE
	@Value("${naver.state}")
	private String state;//RANDOM_STATE
	
	/*
	app.get('/naverLogin', function (req, res) {
	  api_url = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + client_id + '&redirect_uri=' + redirectURI + '&state=' + state;
	   res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
	   res.end("<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>");
	});
	*/
	@GetMapping("/naverLogin")//http://localhost:9010/api/naverLogin
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}
	
	/*
	url에 {}=변수명 표시가 없으면 : @RequestParam이나 @RequestBody
	                          @RequestParam : 특정 부분만 가져올 때
	                          @RequestBody : 모든 부분을 가져올 때
	url에 {}=변수명 표시가 있으면 : @PathVariable, {}안에 있는 변수명에 값을 집어넣는다
	*/
	@GetMapping("/callback")//http://localhost:9010/api/callback
	public String callback(@RequestParam String code, @RequestParam String state) {
		//네이버에서 로그인을 성공하고 성공했을 때 받는 도장(입국심사 도장 비슷하게)
		//1. client-id		 : 어느 회사에 들어왔는지
		//2. client-secret	 : 회사에 들어오기 위한 비밀번호
		//3. redirectUri	 : 들어오기 위한 심사를 무사히 완료했으면 내보내는 위치 설정
		//4. code			 : 네이버로부터 무사히 들어왔다는 인증코드를 받음
		//5. state			 : CSRF 공격을 방지하기 위해 사용
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code + "&state=" + state;
		RestTemplate restTemplate = new RestTemplate();
	  //RestTemplate : HTTP 메서드 GET, POST, PUT, DELETE 통해서
	  //               데이터를 JSON 형식으로 데이터를 처리
		String 응답결과 = restTemplate.getForObject(api_url, String.class);
	  //String.class : api_url 주소로 응답받은 결과를 String(=문자열)로 가져와서 사용하겠다.
		return 응답결과;
	}
}
