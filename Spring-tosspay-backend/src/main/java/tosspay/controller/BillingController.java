package tosspay.controller;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/billing")
public class BillingController {
	//value값 이용해서 apiSecretKey 가져오기
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	//RestTemplate header와 body 최종으로 작성할 공간 생성
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode((secretKey + ":").getBytes()));
	}
	
	//Map 같이 만들기
	private final Map<String, String> billingMap = new ConcurrentHashMap<>();
	
	//server.js에서 confirm-billing url을 참조해서 코드 완성하기
	@PostMapping("/confirm-billing")
	public ResponseEntity<?> confirmBilling(@RequestBody Map<String, String> requestBody) {
		String billingKey = billingMap.get(requestBody.get("customerKey"));
		//fetch(`https://api.tosspayments.com/v1/billing/${billingKeyMap.get(customerKey)}`, {
		String url = "https://api.tosspayments.com/v1/billing" + billingKey;
		//HttpHeaders와 return new까지 완성
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", encodeSecretKey(apiSecretKey));
		headers.set("Content-Type", "application/json");
		
		//타입 key-value 타입 MAp을 이용해서 String, String 모두 문자열로 가지고 오겠다.
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(requestBody, headers);
		
		ResponseEntity<Map> res = restTemplate.exchange(url,  HttpMethod.POST, entity, Map.class);
		//requestBody = 본문, 고객이 작성한 키 값
		//billingKey : 정기결제에 관련된 키 값이 들어있음
		billingMap.put(requestBody.get("customerKey"), res.getBody().get("billingKey").toString());
		return new ResponseEntity<>(res.getBody(), res.getStatusCode());
	}
}
