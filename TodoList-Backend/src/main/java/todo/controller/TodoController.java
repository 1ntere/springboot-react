package todo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.dto.TodoMember;
import todo.service.TodoService;

@RestController
//Controller : DTO와 Service의 URL 주소 연결
public class TodoController {
	@Autowired
	private TodoService service;
	
	/** 아이디 중복검사
	 * @param id
	 * @Return 중복 : 1, 사용가능 : 0
	 *         중복 = select Count(*)를 했을 때 만약에 사용하고자 하는 아이디가 존재하면 
	 *               Count 값이 1로 넘어오고
	 *               사용하고자 하는 아이디가 DB에 존재하지 않으면 0이 넘어옴
	 */
	@GetMapping("idCheck")
	public int idCheck(@RequestParam("id") String id) {
		return service.idCheck(id);
	}
	
	/** 회원가입
	 * @param member
	 * @return 성공 : 1, 실패 : 0
	 */
	@PostMapping("/signup")
	public int signup(@RequestBody TodoMember member) {
		return service.signup(member);
	}

	/** 로그인
	 * @param member
	 * @return 성공 : 회원정보의 /todolist, 실패 : null
	 */
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody TodoMember member) {
		return service.login(member);
	}
}
