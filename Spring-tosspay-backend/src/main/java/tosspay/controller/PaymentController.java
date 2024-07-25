package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController //html 파일이 아니라 url 주소 값으로 연동
@RequestMapping("/confirm")
public class PaymentController {
	@Value("${widgetSecretKey}")
  //@Value() : application.properties에 설정 키 이름을 가져오기 위해 value
	      //${} : 특정한 키 이름을 외부나 다른 곳에서 가져와 사용할 때는 ${키이름}
	private String widgetSecretKey;

	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode((secretKey + ":").getBytes()));
	}
	
	//widget이라는 주소로 결제정보가 들어오면 결제확인창구로 넘겨주는 것
	//-> 결제정보와 결제하고자하는 사용자의 비밀번호
	@PostMapping("/widget")
	public ResponseEntity<?> confirmWidget(@RequestBody Map<String, String> requestBody) {
		return confirmPayment(requestBody, encodeSecretKey(widgetSecretKey));
	}
	
	//payment라는 주소로 결제 정보가 들어오면 결제확인창구로 넘겨주는 것
	//-> 결제정보와 결제하고자하는 사용자의 비밀번호
	@PostMapping("/payment")
	public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> requestBody) {
		return confirmPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	@PostMapping("/brandpay")
	public ResponseEntity<?> confirmBrandpay(@RequestBody Map<String, String> requestBody) {
		return confirmBrandPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	private ResponseEntity<?> confirmPayment(Map<String, String> requestBody, String encodedKey) {
		//fetch("https://api.tosspayments.com/v1/payments/confirm"), {
		String url = "https://api.tosspayments.com/v1/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		//	Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);//encryptedApiSecretKey를 위에서 encodedKey라고 작성해줌
		//	"Content-Type": "application/json",
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}
	
	private ResponseEntity<?> confirmBrandPayment(Map<String, String> requestBody, String encodedKey) {
		//fetch("https://api.tosspayments.com/v1/payments/confirm"), {
		String url = "https://api.tosspayments.com/v1/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		//	Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);//encryptedApiSecretKey를 위에서 encodedKey라고 작성해줌
		//	"Content-Type": "application/json",
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		//결제 성공했을 때와 실패했을 때
		
		try {
			ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
			return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			//         사용자한테 보내는 응답<>(실패메세지    (), 잘못된 요청으로 실패.상태코드 보낸 것); 
		}
	}
}
/*****
[Entity]
HttpEntity		 : HTTP 요청 또는 응답의 본문(body)와 헤더(headers)를 포함하는 객체
                   HTTP 요청을 보낼 때 본문과 헤더를 설정하고자 할 때 사용
                   본문(body) : 실제 전송될 데이터 ex) 아이디, 비밀번호, 작성한 글 등
                   헤더(headers) : HTTP 헤더 정보를 포함 ex) 글자인지, 이미지인지, 동영상인지, 어떤 파일이 들어오는 것인지? 누가 보내는지?
	HttpEntity<문자열이면 문자열, 숫자면 숫자, 모르겠으면 비워두기> abc = new HttpEntity<비워져있어도 상관 없음>("요청 본문", headers);
ResponseEntity	 : (Response = 응답 / Http를 상속받아서 Http 기능의 응답에 대한 기능을 추가로 설정한 Entity)
                   HttpEntity를 상속받아, Http 응답에 대한 추가적인 정보를 제공
                   상태코드를 포함하고 있어서 클라이언트(사용자)에게 응답을 보낼 때 사용
                   사용자가 요청한 응답을 개발자가 다시 사용자한테 전달할 때 사용
    ResponseEntity<String이면 String, Integer이면 Integer, 여러 값이면 ?, 모르겠으면 비워두기> = new ResponseEntity<>("응답본문", headers);
    ResponseEntity<String> res = new ResponseEntity<>("응답본문", headers);
RequestEntity	 : (Request = 요청 / Http를 상속받아서 Http 기능의 요청에 대한 기능을 추가로 설정한 Entity)
                   HttpEntity를 상속받아, Http 요청에 대한 추가적인 정보를 제공
                   URL와 HTTP메서드 (GET, POST, PUT, DELETE)을 포함하고 있어, 서버로 요청을 보낼 때 주로 사용
	RequestEntity<String이면 String, Integer이면 Integer, 여러 값이면 ?, 모르겠으면 비워두기> = new RequestEntity<>("응답본문", headers);
	RequestEntity<String> req = new RequestEntity<>("응답본문", headers, HttpMethod.POST, url);

[상속도]
HttpEntity
	├ ResponseEntity
	└ RequestEntity

[차이점 요약하기]
클래스			상속관계			주요 사용 목적					추가 정보
HttpEntity		기본 클래스			Http 요청/응답, 본문과 헤더 포함	상태코드(성공/실패)없음
ResponseEntity	HttpEntity 상속	Http 응답 반환					상태코드(성공/실패)포함
RequestEntity	HttpEntity 상속	Http 요청 전송					URI와 Http메서드 포함

Http : 웹에서 데이터를 전송하기 위한 전송수단
**********
URI : 주소값과 식별값이 들어있음, 이 안에 URL이 들어있음
URL : URI의 한 종류로 주소이름
URN : 고유한 이름

[상속도]
URI
├ URN : 고유식별번호
└ URL : 도메인명
*/